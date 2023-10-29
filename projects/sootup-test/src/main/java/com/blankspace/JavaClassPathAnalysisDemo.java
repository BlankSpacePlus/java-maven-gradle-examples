package com.blankspace;

import java.util.Collections;

import sootup.core.Project;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.model.SootClass;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.core.views.View;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaProject;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootClassSource;
import sootup.java.core.language.JavaLanguage;
import sootup.java.core.views.JavaView;

public class JavaClassPathAnalysisDemo {
    public static void main(String[] args) {
        // Creating a Project
        AnalysisInputLocation<JavaSootClass> inputLocation = new JavaClassPathAnalysisInputLocation("<BinaryFilePath>");
        JavaLanguage language = new JavaLanguage(8);
        Project<JavaSootClass, JavaView> project = JavaProject.builder(language).addInputLocation(inputLocation).build();
        // Retrieving a Class
        ClassType classType = project.getIdentifierFactory().getClassType("com.blankspace.HelloWorld");
        // Retrieving a Method
        MethodSignature methodSignature = project.getIdentifierFactory().getMethodSignature(classType, "main", "void", Collections.singletonList("java.lang.String[]"));
        // Creating a View
        View<JavaSootClass> view = project.createFullView();
        SootClass<JavaSootClassSource> sootClass = view.getClass(classType).get();
        SootMethod sootMethod = sootClass.getMethod(methodSignature.getSubSignature()).get();
        // Retrieving the Control-Flow Graph of a Method
        System.out.println(sootMethod.getBody().getStmts());
    }
}