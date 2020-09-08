package com.example.compiler;

import com.m.k.anotaion.MvpEntity;
import com.m.k.anotaion.Umeng;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public class UmengGenerator implements FileGenerator {
    private static final String PROPERTIES_FILE_NAME = "./mvplib/mvp.properties";

    private File configFile;
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(Umeng.class.getCanonicalName());
        return types;
    }

    public UmengGenerator(File file) {
        configFile = file;
    }

    @Override
    public boolean process(Elements elements, Messager messager, Filer filer, Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        Set<? extends Element> entityElements  = roundEnvironment.getElementsAnnotatedWith(Umeng.class);

        if(entityElements == null || entityElements.size() == 0){
            return false;
        }





        ClassName name = ClassName.get("com.umeng.socialize.weixin.view","WXCallbackActivity");
        TypeSpec.Builder builder = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(name);

        AnnotationMirror annotationMirror = entityElements.iterator().next().getAnnotationMirrors().get(0);
        String applicationId = annotationMirror.getElementValues().values().iterator().next().getValue().toString();

        JavaFile file = JavaFile.builder(applicationId +".wxapi", builder.build()).build();

        try {
            file.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
