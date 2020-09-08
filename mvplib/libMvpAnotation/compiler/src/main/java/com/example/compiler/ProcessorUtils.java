package com.example.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class ProcessorUtils  {
    private static final String PROPERTIES_FILE_NAME = "./mvplib/mvp.properties";
    private Elements mElementUtils;
    private Messager mMessager;
    private Filer mFiler;

    protected File configFile;

    private ArrayList<FileGenerator> mGenerators;

    public ProcessorUtils(Elements mElementUtils, Messager mMessager, Filer mFiler) {
        this.mElementUtils = mElementUtils;
        this.mMessager = mMessager;
        this.mFiler = mFiler;
        try {
            JavaFileObject dummySourceFile = mFiler.createSourceFile("dummy" + System.currentTimeMillis());
            mMessager.printMessage(Diagnostic.Kind.WARNING,dummySourceFile.toUri().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        configFile = new File(PROPERTIES_FILE_NAME);

        if(!configFile.exists()){
            mMessager.printMessage(Diagnostic.Kind.WARNING,configFile.getAbsolutePath() + " 不存在");
            String path = configFile.getAbsolutePath();
            path = path.substring(0,path.indexOf(PROPERTIES_FILE_NAME));
            configFile = new File(new File(path).getParent(),PROPERTIES_FILE_NAME.substring(1,PROPERTIES_FILE_NAME.length()));

        }
        mMessager.printMessage(Diagnostic.Kind.WARNING,configFile.getAbsolutePath());


        mGenerators = new ArrayList<>();


        mGenerators.add(new ConfigGenerator(configFile));
        mGenerators.add(new EntityGenerator());
        mGenerators.add(new UmengGenerator(configFile));



    }


    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        for(FileGenerator generator : mGenerators){
            types.addAll(generator.getSupportedAnnotationTypes());
        }
        return types;
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for(FileGenerator generator : mGenerators){
            generator.process(mElementUtils,mMessager,mFiler,set,roundEnvironment);
        }

        return false;
    }
}
