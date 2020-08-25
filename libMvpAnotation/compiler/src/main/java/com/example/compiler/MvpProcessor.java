package com.example.compiler;

import com.google.auto.service.AutoService;
import com.m.k.anotaion.IMvpEntity;
import com.m.k.anotaion.MvpEntity;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class MvpProcessor extends AbstractProcessor {
    private Elements mElementUtils;
    private Messager mMessager;
    private Filer mFiler;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mElementUtils = processingEnvironment.getElementUtils();    // 元素操作辅助工具
        mMessager = processingEnvironment.getMessager();            // 日志辅助工具
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(MvpEntity.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elements  = roundEnvironment.getElementsAnnotatedWith(MvpEntity.class);

        for(Element element : elements){
            //检查element类型
            if (!checkAnnotationValid(element,MvpEntity.class)){
                return false;
            }

            TypeElement typeElement = (TypeElement) element;

            String className = typeElement.getSimpleName().toString();
            String packageName = mElementUtils.getPackageOf(element).getQualifiedName().toString();

            genneratorClass(className,packageName);

        }

        return false;
    }



    private void genneratorClass(String className,String packageName){

        ParameterizedTypeName activity = ParameterizedTypeName.get(ClassName.get(packageName,className),TypeVariableName.get("D"));

        TypeSpec.Builder mainActivityBuilder = TypeSpec.classBuilder("ProxyEntity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(activity)
                .addTypeVariable(TypeVariableName.get("D"));






        TypeSpec mainActivity = mainActivityBuilder
                .build();




        JavaFile file = JavaFile.builder(packageName, mainActivity).build();

        try {
            file.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkAnnotationValid(Element annotatedElement, Class clazz)
    {
        if (annotatedElement.getKind() != ElementKind.CLASS)
        {
            return false;
        }
        if (annotatedElement.getModifiers().contains(Modifier.PRIVATE))
        {
            return false;
        }

        return true;
    }


}
