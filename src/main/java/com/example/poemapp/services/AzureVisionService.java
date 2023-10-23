package com.example.poemapp.services;

import com.azure.ai.vision.common.VisionServiceOptions;
import com.azure.ai.vision.common.VisionSource;
import com.azure.ai.vision.imageanalysis.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Arrays;
import java.util.EnumSet;

@Service
public class AzureVisionService {

    @Value("${VISION_ENDPOINT}")
    private String endpoint;

    @Value("${VISION_KEY}")
    private String key;

    public String analyzeImageFromFile(String filePath) {

        String res = "";

        try (
                VisionServiceOptions serviceOptions = new VisionServiceOptions(new URL(endpoint), key);

                // Specify the image file on disk to analyze. sample.jpg is a good example to show most features.
                // Alternatively, specify an image URL (e.g. https://aka.ms/azai/vision/image-analysis-sample.jpg)
                // or a memory buffer containing the image. see:
                // https://learn.microsoft.com/azure/ai-services/computer-vision/how-to/call-analyze-image-40?pivots=programming-language-java#select-the-image-to-analyze
                VisionSource imageSource = VisionSource.fromFile(filePath);

                ImageAnalysisOptions analysisOptions = new ImageAnalysisOptions()) {

            // Mandatory. You must set one or more features to analyze. Here we use the full set of features.
            // Note that 'Caption' and 'DenseCaptions' are only supported in Azure GPU regions (East US, France Central, Korea Central,
            // North Europe, Southeast Asia, West Europe, West US). Remove 'Caption' and 'DenseCaptions' from the list below if your
            // Computer Vision key is not from one of those regions.
            analysisOptions.setFeatures(EnumSet.of(
                    ImageAnalysisFeature.CROP_SUGGESTIONS,
                    ImageAnalysisFeature.CAPTION,
                    ImageAnalysisFeature.DENSE_CAPTIONS,
                    ImageAnalysisFeature.OBJECTS,
                    ImageAnalysisFeature.PEOPLE,
                    ImageAnalysisFeature.TEXT,
                    ImageAnalysisFeature.TAGS));

            // Optional, and only relevant when you select ImageAnalysisFeature.CropSuggestions.
            // Define one or more aspect ratios for the desired cropping. Each aspect ratio needs to be in the range [0.75, 1.8].
            // If you do not set this, the service will return one crop suggestion with the aspect ratio it sees fit.
            analysisOptions.setCroppingAspectRatios(Arrays.asList(0.9, 1.33));

            // Optional. Default is "en" for English. See https://aka.ms/cv-languages for a list of supported
            // language codes and which visual features are supported for each language.
            analysisOptions.setLanguage("en");

            // Optional. Default is "latest".
            analysisOptions.setModelVersion("latest");

            // Optional, and only relevant when you select ImageAnalysisFeature.Caption.
            // Set this to "true" to get a gender neutral caption (the default is "false").
            analysisOptions.setGenderNeutralCaption(true);

            try (
                    ImageAnalyzer analyzer = new ImageAnalyzer(serviceOptions, imageSource, analysisOptions)) {
                System.out.println(" Please wait for image analysis results...\n");

                // This call creates the network connection and blocks until Image Analysis results
                // return (or an error occurred). Note that there is also an asynchronous (non-blocking)
                // version of this method: analyzer.analyzeAsync().
                try(
                        ImageAnalysisResult result = analyzer.analyze()) {

                    if (result.getReason() == ImageAnalysisResultReason.ANALYZED) {
                        System.out.println(" Image height = " + result.getImageHeight());
                        System.out.println(" Image width = " + result.getImageWidth());
                        System.out.println(" Model version = " + result.getModelVersion());

                        if (result.getCaption() != null) {
                            res+= "Напиши стих на тему: " + result.getCaption().getContent() + "\n";
                            System.out.println(" Caption:");
                            System.out.println("   \"" + result.getCaption().getContent() + "\", Confidence "
                                    + String.format("%.4f", result.getCaption().getConfidence()));
                        }

                        if (result.getDenseCaptions() != null) {
                            System.out.println(" Dense Captions:");
                            for (ContentCaption denseCaption : result.getDenseCaptions()) {
                                System.out.println("   \"" + denseCaption.getContent() + "\", Bounding box "
                                        + denseCaption.getBoundingBox() +
                                        ", Confidence " + String.format("%.4f", denseCaption.getConfidence()));
                            }
                        }

                        if (result.getObjects() != null) {
                            System.out.println(" Objects:");
                            for (DetectedObject detectedObject : result.getObjects()) {
                                System.out.println("   \"" + detectedObject.getName() + "\", Bounding box "
                                        + detectedObject.getBoundingBox() +
                                        ", Confidence " + String.format("%.4f", detectedObject.getConfidence()));
                            }
                        }

                        if (result.getTags() != null) {
                            res+= " Включая слова: ";
                            System.out.println(" Tags:");
                            for (ContentTag tag : result.getTags()) {
                                res+= tag.getName() + ", ";
                                System.out.println("   \"" + tag.getName() + "\", Confidence "
                                        + String.format("%.4f", tag.getConfidence()));
                            }
                        }

                        if (result.getPeople() != null) {
                            System.out.println(" People:");
                            for (DetectedPerson person : result.getPeople()) {
                                System.out.println("   Bounding box " + person.getBoundingBox() +
                                        ", Confidence " + String.format("%.4f", person.getConfidence()));
                            }
                        }

                        if (result.getCropSuggestions() != null) {
                            System.out.println(" Crop Suggestions:");
                            for (CropSuggestion cropSuggestion : result.getCropSuggestions()) {
                                System.out.println("   Aspect ratio " + cropSuggestion.getAspectRatio() +
                                        ": Crop suggestion " + cropSuggestion.getBoundingBox());
                            }
                        }

                        if (result.getText() != null) {
                            System.out.println(" Text:");
                            for (DetectedTextLine line : result.getText()) {
                                String pointsToString = "{" + String.join(",",
                                        line.getBoundingPolygon().stream().map(Object::toString).toArray(String[]::new))
                                        + "}";
                                System.out.println(
                                        "   Line: '" + line.getContent() + "', Bounding polygon " + pointsToString);

                                for (DetectedTextWord word : line.getWords()) {
                                    pointsToString = "{" + String.join(",",
                                            word.getBoundingPolygon().stream().map(Object::toString).toArray(String[]::new))
                                            + "}";
                                    System.out.println(
                                            "     Word: '" + word.getContent() + "', Bounding polygon " + pointsToString +
                                                    ", Confidence " + String.format("%.4f", word.getConfidence()));
                                }
                            }
                        }

                        ImageAnalysisResultDetails resultDetails = ImageAnalysisResultDetails.fromResult(result);
                        System.out.println(" Result details:");
                        System.out.println("   Image ID = " + resultDetails.getImageId());
                        System.out.println("   Result ID = " + resultDetails.getResultId());
                        System.out.println("   Connection URL = " + resultDetails.getConnectionUrl());
                        System.out.println("   JSON result = " + resultDetails.getJsonResult());
                    } else { // result.Reason == ImageAnalysisResultReason.Error
                        ImageAnalysisErrorDetails errorDetails = ImageAnalysisErrorDetails.fromResult(result);
                        System.out.println(" Analysis failed.");
                        System.out.println("   Error reason: " + errorDetails.getReason());
                        System.out.println("   Error code: " + errorDetails.getErrorCode());
                        System.out.println("   Error message: " + errorDetails.getMessage());
                        System.out.println(" Did you set the computer vision endpoint and key?");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
