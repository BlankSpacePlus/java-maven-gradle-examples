package com.blankspace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sootup.callgraph.CallGraph;
import sootup.callgraph.CallGraphAlgorithm;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.core.Project;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.signatures.MethodSignature;
import sootup.core.typehierarchy.TypeHierarchy;
import sootup.core.typehierarchy.ViewTypeHierarchy;
import sootup.core.types.ClassType;
import sootup.core.types.PrimitiveType;
import sootup.core.types.VoidType;
import sootup.core.views.View;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaIdentifierFactory;
import sootup.java.core.JavaProject;
import sootup.java.core.JavaSootClass;
import sootup.java.core.language.JavaLanguage;
import sootup.java.core.views.JavaView;

public class JavaCallGraphDemo {
    public static void main(String[] args) {
        // Create a AnalysisInputLocation, which points to a directory. All class files will be loaded from the directory
        AnalysisInputLocation<JavaSootClass> inputLocation = new JavaClassPathAnalysisInputLocation("D:/IDEA/java-maven-gradle-demo/projects/sootup-test/target/classes");
        // Specify the language of the JavaProject. This is especially relevant for Multi-release jars, where classes are loaded depending on the language level of the analysis
        JavaLanguage language = new JavaLanguage(8);
        // Create a new JavaProject and view based on the input location
        JavaProject project = JavaProject.builder(language).addInputLocation(inputLocation).addInputLocation(new JavaClassPathAnalysisInputLocation(System.getProperty("java.home") + "/lib/rt.jar")).build();
        JavaView view = project.createFullView();
        // Get a MethodSignature
        ClassType classTypeA = project.getIdentifierFactory().getClassType("com.blankspace.ClassA");
        ClassType classTypeB = project.getIdentifierFactory().getClassType("com.blankspace.ClassB");
        MethodSignature entryMethodSignature = JavaIdentifierFactory.getInstance().getMethodSignature(classTypeB, JavaIdentifierFactory.getInstance().getMethodSubSignature("calculate", PrimitiveType.getInt(), Collections.singletonList(classTypeA)));
        // Create type hierarchy and CHA
        final ViewTypeHierarchy typeHierarchy = new ViewTypeHierarchy(view);
        System.out.println(typeHierarchy.subclassesOf(classTypeA));
        CallGraphAlgorithm algorithm = new ClassHierarchyAnalysisAlgorithm(view, typeHierarchy);
        // Create CG by initializing CHA with entry method(s)
        CallGraph callGraph = algorithm.initialize(Collections.singletonList(entryMethodSignature));
        callGraph.callsFrom(entryMethodSignature).forEach(System.out::println);
    }
}
