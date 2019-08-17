package com.cognizant.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.cognizant.beans.Layers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity {
                static String htmlContent = " ";
                static String cssContent = " ";
                static Stack<String> stack = new Stack<String>();
                static Stack<Float> guideX = new Stack<Float>();
                static Stack<Float> guideY = new Stack<Float>();
                static Stack<String> cssStack = new Stack<String>();
                static String parenttext = " ", childtext = " ", subchildtext = " ";
                static int valX = 0;
                static int valY = 0;
                private static Scanner sc;

                public static void main(String[] args) throws FileNotFoundException {

                                Gson gson = new Gson();
                                String path = "C:\\Users\\765749\\Documents\\json\\regionDetails_XD.json";
                                //String path1 = "C:\\Users\\765749\\Documents\\demo1.json";
                                BufferedReader br = new BufferedReader(new FileReader(path));
                                //BufferedReader br1 = new BufferedReader(new FileReader(path1));
                                Map<String, Layers> decoded = gson.fromJson(br, new TypeToken<Map<String, Layers>>() {
                                }.getType());
                                 //Map<String, Layers> decodedMobile = gson.fromJson(br1,
                                 //new TypeToken<Map<String, Layers>>() {
                                //}.getType());


                                // Creating HTML and CSS File
                                try {
                                                File htmlFile = new File("C:\\Users\\765749\\Documents\\generatedHTML.html");
                                                File cssFile = new File("C:\\Users\\765749\\Documents\\generatedCSS.css");

                                                if (htmlFile.createNewFile() && cssFile.createNewFile()) {

                                                                // Writing HTML and CSS File
                                                                FileWriter htmlWriter = new FileWriter(htmlFile);
                                                                FileWriter cssWriter = new FileWriter(cssFile);
                                                                BufferedWriter htmlBuffer = new BufferedWriter(htmlWriter);
                                                                BufferedWriter cssBuffer = new BufferedWriter(cssWriter);

                                                                //while()



                                                                htmlBuffer.write(
                                                                                                "<html>\n<head>\n<link rel = \"stylesheet\" type = \"text/css\" href = \"generatedCSS.css\">\n<link rel = \"stylesheet\" type = \"text/css\" href = \"CSS\\colors.css\">\n</head>\n<body>\n");
                                                                for (String layerName : decoded.keySet()) {

                                                                                Layers layerValue = decoded.get(layerName);
                                                                                List<String> layerParent = layerValue.getParent();
                                                                                Map<String, String> cssStyles = layerValue.getCssStyles();
                                                                                for (String parent : layerParent) {

                                                                                                if (parent.equals("ROOT_REF_WINDOW")) {
                                                                                                                //if(parent.equals("screen"))
                                                                                                //            {

                                                                                                                guideX.push((float) 0.00);
                                                                                                                guideY.push((float) 0.00);
                                                                                                                cssStack.push(layerValue.getElementName());
                                                                                                                computeStyleGuide(decoded, guideX, guideY, cssStack);
                                                                                                                htmlContent = htmlContent + "\n<div id = \"" + layerValue.getElementName() + "\">\n";
                                                                                                                List<String> layerContains = layerValue.getContains();
                                                                                                                                if (layerContains.isEmpty())
                                                                                                                                {
                                                                                                                                                htmlContent = htmlContent + "\n</div>";
                                                                                                                                }
                                                                                                                                else
                                                                                                                                {
                                                                                                                                                for (String cont : layerContains) {
                                                                                                                                                                stack.push(cont);
                                                                                                                                                }

                                                                                                                                                pushFunction(decoded, stack);
                                                                                                                                                System.out.println(stack);
                                                                                                                                                htmlContent = htmlContent + "</div>";
                                                                                                                                }
                                                                                                //            }
                                                                                                }
                                                                                }

                                                                }
                                                                for (String layerName : decoded.keySet()) {

                                                                                if (!layerName.equals("ROOT_REF_WINDOW")) {

                                                                                                Layers layerValue = decoded.get(layerName);
                                                                                                List<String> layerParent = layerValue.getParent();
                                                                                                // System.out.println(layerName);
                                                                                                Map<String, String> cssStyles = layerValue.getCssStyles();
                                                                                                //parenttext = layerValue.getText();
                                                                                                //System.out.println(" "+parenttext);

                                                                                                String Layer = layerValue.getElementName();
                                                                                                String reLayer=Layer.replace(".","_");
                                                                                                if(cssStyles!=null)
                                                                                                {
                                                                                                	if(layerValue.getElementType().equals("header") || layerValue.getElementType().equals("footer"))
                                                                                                	{
                                                                                                		cssContent = cssContent  + reLayer + "{\nposition:absolute;\nborder:solid 1px black;\n";

                                                                                                	}
                                                                                                	else
                                                                                                	{
                                                                                                cssContent = cssContent + "#" + reLayer + "{\nposition:absolute;\nborder:solid 1px black;\n";
                                                                                                	}
                                                                                                cssContent = cssContent + "height:" + layerValue.getHeight() + ";\n";
                                                                                                cssContent = cssContent + "width:" + layerValue.getWidth() + ";\n";
                                                                                                cssContent = cssContent + "left:" + layerValue.getStyleGuideX() + ";\n";
                                                                                                cssContent = cssContent + "top:" + layerValue.getStyleGuideY() + ";\n";

                                                                                                for (String map : cssStyles.keySet()) {
                                                                                                                // System.out.println(map);
                                                                                                                cssContent = cssContent + map + ":" + cssStyles.get(map) + ";\n";
                                                                                                }
                                                                                                cssContent = cssContent + "}\n";
                                                                                                // htmlBuffer.write(htmlContent);

                                                                                }

                                                                                // File Close

                                                                }


                                                                }

                                                                cssBuffer.write(cssContent);
                                                                htmlBuffer.write(htmlContent);
                                                                htmlBuffer.write("\n</body>\n</html>");
                                                                htmlBuffer.close();
                                                                cssBuffer.close();
                                                }
                                                else {
                                                                System.out.println("file already exists");
                                                }
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }

                }

                private static void computeStyleGuide(Map<String, Layers> decoded, Stack<Float> guideX2, Stack<Float> guideY2,
                                                Stack<String> cssStack2) {
                                if (!cssStack2.peek().equals("ROOT_REF_WINDOW")) {
                                                while (!cssStack2.isEmpty()) {
                                                                if (cssStack2.peek().equals("-1")) {
                                                                                guideX2.pop();
                                                                                guideY2.pop();
                                                                                cssStack2.pop();
                                                                } else {
                                                                                Layers layer = decoded.get(cssStack2.peek());
                                                                                List<String> layerContains = layer.getContains();
                                                                                String s = layer.getStyleGuideX();
                                                                                String newX = s.substring(0, s.length() - 2);
                                                                                String lasting = s.substring(s.length() - 2, s.length());
                                                                                Float layX = Float.parseFloat(newX);
                                                                                String s1 = layer.getStyleGuideY();
                                                                                String lasting1 = s1.substring(s1.length() - 2, s1.length());
                                                                                String newY = s1.substring(0, s1.length() - 2);
                                                                                Float layY = Float.parseFloat(newY);
                                                                                if (layerContains.isEmpty()) {

                                                                                                float resX = Math.abs(layX - guideX2.peek());

                                                                                                float resY = Math.abs(layY - guideY2.peek());
                                                                                                layer.setStyleGuideX(String.valueOf(resX) + lasting);
                                                                                                layer.setStyleGuideY(String.valueOf(resY) + lasting1);
                                                                                                cssStack2.pop();

                                                                                } else {
                                                                                                // cssStack2.push(layX - guideX2.peek());
                                                                                                float setX = Math.abs(layX - guideX2.peek());
                                                                                                layer.setStyleGuideY(String.valueOf(setX) + lasting);
                                                                                                float setY = Math.abs(layY - guideY2.peek());
                                                                                                layer.setStyleGuideY(String.valueOf(setY) + lasting1);
                                                                                                cssStack2.pop();
                                                                                                cssStack2.push("-1");
                                                                                                float tempX = layX;
                                                                                                guideX2.push(tempX);
                                                                                                float tempY = layY;
                                                                                                guideY2.push(tempY);

                                                                                                for (String tmpString : layerContains) {
                                                                                                                cssStack2.push(tmpString);

                                                                                                }

                                                                                }
                                                                }
                                                }
                                }

                }

                private static void pushFunction(Map<String, Layers> decoded, Stack<String> stack2) {
                                // int val x = lay.stx, int valy =
                                //System.out.println(stack2);
                                String elementType=" ";
                                int navFlag = 0;
                                while(!stack2.isEmpty())
                                {

                                    if (stack2.peek().equals("div")) {
                                    							htmlContent = htmlContent + "\n</div>";
                                                    			stack2.pop();
                                    }
                                    else if (stack2.peek().equals("headerLayer")) {
            													htmlContent = htmlContent + "\n</header>";
            													stack2.pop();
                                    }
                                    else if (stack2.peek().equals("footerLayer")) {
            													htmlContent = htmlContent + "\n</footer>";
            													stack2.pop();
                                    }
                                    else if (stack2.peek().equals("heading")) {
                                                                htmlContent = htmlContent + "\n</h1>";
                                                                stack2.pop();
                                                }
                                    else if (stack2.peek().equals("button")) {
                                                                htmlContent = htmlContent + "\n</button>";
                                                                stack2.pop();
                                                }
                                    else if (stack2.peek().equals("text")) {
                                                                htmlContent = htmlContent + "\n</span>";
                                                                stack2.pop();
                                                }
                                    else if (stack2.peek().equals("image")) {

                                                    stack2.pop();
                                                }
                                    else if (stack2.peek().equals("icon")) {

                                                    stack2.pop();
                                                }
                                    else
                                    {
                                                Layers subLayer = decoded.get(stack2.peek());
                                                elementType = subLayer.getElementType();
                                                List<String> contains = subLayer.getContains();
                                                String Layer = subLayer.getElementName();
                                                String reLayer=Layer.replace(".","_");
                                                if(contains.isEmpty())
                                                {
                                                                if(elementType.equals("heading"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<h1 id = \"" + reLayer + "\">" +subLayer.getText()+ "\n</h1>\n";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("header"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<header>\n</header>\n";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("footer"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<footer>\n</footer>\n";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("navlink"))

                                                                {

                                                                                htmlContent = htmlContent+"\n<a href=\"#\" id =\"" + reLayer + "\">" +subLayer.getText()+ "\n</a>\n";
                                                                                stack2.pop();
                                                                                navFlag--;
                                                                                if(navFlag==0)
                                                                                {
                                                                                	htmlContent=htmlContent+"</nav>";
                                                                                }

                                                                }
                                                                else if(elementType.equals("image"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<img id = \"" + reLayer + "\" src=\"image.jpg\" alt=\"this is image\" >";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("text"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<span id = \"" + reLayer + "\">" +subLayer.getText()+ "\n</span>\n";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("icon"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<img id = \"" + reLayer + "\" src=\"icon.png\" alt=\"this is icon\" >\n";
                                                                                stack2.pop();
                                                                }
                                                                else if(elementType.equals("button"))
                                                                {
                                                                                htmlContent = htmlContent+"\n<button id = \"" + reLayer + "\"></button>\n";
                                                                                stack2.pop();
                                                                }
                                                                else
                                                                {
                                                                                htmlContent = htmlContent +"\n<div id = \"" + reLayer + "\"></div>\n";
                                                                                stack2.pop();
                                                                }
                                                }

                                                else
                                                {
                                                                stack2.pop();

                                                                if(elementType.equals("heading"))
                                                                {
                                                                                htmlContent = htmlContent+"<h1 id = \"" + reLayer + "\">" +subLayer.getText();
                                                                                stack2.push("heading");
                                                                }
                                                                else if(elementType.equals("header"))
                                                                {
                                                                                htmlContent = htmlContent+"<header>";
                                                                                stack2.push("headerLayer");
                                                                }
                                                                else if(elementType.equals("footer"))
                                                                {
                                                                                htmlContent = htmlContent+"<footer>" ;
                                                                                stack2.push("footerLayer");
                                                                }
                                                                else if(elementType.equals("image"))
                                                                {
                                                                                htmlContent = htmlContent+"<img id = \"" + reLayer + "\" src=\"image.jpg\" alt=\"this is image\" >\n";
                                                                                stack2.push("image");

                                                                }
                                                                else if(elementType.equals("text"))
                                                                {
                                                                                htmlContent = htmlContent+"<span id = \"" + reLayer + "\">" +subLayer.getText()+ "</span>\n";
                                                                                stack2.push("text");

                                                                }
                                                                else if(elementType.equals("icon"))
                                                                {
                                                                                htmlContent = htmlContent+"<img id = \"" + reLayer + "\" src=\"icon.png\" alt=\"this is icon\" >\n";
                                                                                stack2.push("icon");

                                                                }
                                                                else if(elementType.equals("button"))
                                                                {
                                                                                htmlContent = htmlContent+"<button id = \"" + reLayer + "\">\n";
                                                                                stack2.push("button");

                                                                }
                                                                else
                                                                {
                                                                                htmlContent = htmlContent +"<div id = \"" + reLayer + "\">\n";
                                                                                stack2.push("div");

                                                                }
                                                                for(String cont: contains)
                                                                {
                                                                				Layers checkNav = decoded.get(cont);
                                                                				String type=checkNav.getElementType();

                                                                				if(type.equals("navlink"))
                                                                				{
                                                                					if(navFlag==0)
                                                                    					htmlContent=htmlContent+"\n<nav>";
                                                                					navFlag++;
                                                                				}
                                                                                stack2.push(cont);
                                                                }

                                                }
                                    }


                                }









                                }

                }

