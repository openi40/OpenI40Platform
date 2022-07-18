package com.openi40.scheduler.engine.contextualplugarch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import org.kohsuke.MetaInfServices;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = { "com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation",
		"com.openi40.scheduler.engine.contextualplugarch.AlternativeImplementation" })
@MetaInfServices(value = javax.annotation.processing.Processor.class)
public class ImplementationAnnotationProcessor extends AbstractProcessor {

	public ImplementationAnnotationProcessor() {

	}

	void createServiceCode(String serviceName, String dataType, String businessIfaceName, String factoryName,
			String implementationClass, String key, String switchImplementationProperty, boolean defaultFactory,
			PrintWriter out) {
		if (switchImplementationProperty != null && switchImplementationProperty.trim().length() == 0)
			switchImplementationProperty = null;
		out.println("package com.openi40.scheduler.engine.contextualplugarch.generated;");
		out.println("import com.openi40.scheduler.engine.contextualplugarch.*;");
		out.println("import org.springframework.context.annotation.*;");
		out.println("import org.springframework.beans.factory.annotation.*;");
		out.println("import org.springframework.stereotype.*;");
		out.println("@Service");
		if (defaultFactory) {
			out.println("@Qualifier(\"defaultFactory\")");
		}

		out.println("public class " + serviceName + " extends BusinessLogicFactory<" + businessIfaceName + ", "
				+ dataType + "> implements " + factoryName + "{");
		out.println("public " + serviceName + "(){super(" + businessIfaceName + ".class," + implementationClass
				+ ".class,\"" + key + "\");this.switchingImplementationProperty="
				+ (switchImplementationProperty != null ? "\"" + switchImplementationProperty + "\"" : "null") + ";}");
		out.println("}");
	}

	String getSimpleName(String qualifiedName) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
	}

	void implementThis(TypeElement typeElement, boolean defaultImplementation) {
		String qName = typeElement.getQualifiedName().toString();
		String simpleTypeName = typeElement.getSimpleName().toString();
		String serviceName = simpleTypeName + (defaultImplementation ? "DefaultImplementationFactory" : "Factory");
		System.out.println("Producing default factory for " + qName);
		String implementingClass = typeElement.getQualifiedName().toString();
		List<? extends AnnotationMirror> annotations = typeElement.getAnnotationMirrors();
		for (AnnotationMirror annotationMirror : annotations) {
			if (defaultImplementation && annotationMirror.getAnnotationType().asElement().getSimpleName().toString()
					.equals("DefaultImplementation")) {
				try {
					String dataType = null;
					String businessIfaceName = null;
					String factoryName = null;
					String key = "DEFAULT";
					String switchImplementationProperty = null;
					Map<? extends ExecutableElement, ? extends AnnotationValue> attributes = annotationMirror
							.getElementValues();
					for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> attribute : attributes
							.entrySet()) {
						if (attribute.getKey().getSimpleName().toString().equals("implemented")) {
							businessIfaceName = attribute.getValue().getValue().toString().replace(".class", "");
							factoryName = getSimpleName(businessIfaceName + "Factory");
						}
						if (attribute.getKey().getSimpleName().toString().equals("entityClass")) {
							dataType = attribute.getValue().getValue().toString().replace(".class", "");

						}
						if (attribute.getKey().getSimpleName().toString().equals("key")) {
							key = attribute.getValue().getValue().toString();
						}
						if (attribute.getKey().getSimpleName().toString().equals("switchImplementationProperty")) {
							switchImplementationProperty = attribute.getValue().getValue().toString();
						}
					}
					JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(
							"com.openi40.scheduler.engine.contextualplugarch.generated." + serviceName);
					PrintWriter out = new PrintWriter(builderFile.openWriter());

					createServiceCode(serviceName, dataType, businessIfaceName, factoryName, implementingClass, key,
							switchImplementationProperty, true, out);
					out.flush();
					out.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (!defaultImplementation && annotationMirror.getAnnotationType().asElement().getSimpleName().toString()
					.equals("AlternativeImplementation")) {
				try {
					String dataType = null;
					String businessIfaceName = null;
					String factoryName = null;
					String key = null;
					String switchImplementationProperty = null;
					Map<? extends ExecutableElement, ? extends AnnotationValue> attributes = annotationMirror
							.getElementValues();
					for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> attribute : attributes
							.entrySet()) {
						if (attribute.getKey().getSimpleName().toString().equals("implemented")) {
							businessIfaceName = attribute.getValue().getValue().toString().replace(".class", "");
							factoryName = getSimpleName(businessIfaceName + "Factory");
						}
						if (attribute.getKey().getSimpleName().toString().equals("entityClass")) {
							dataType = attribute.getValue().getValue().toString().replace(".class", "");

						}
						if (attribute.getKey().getSimpleName().toString().equals("key")) {
							key = attribute.getValue().getValue().toString();
						}
						if (attribute.getKey().getSimpleName().toString().equals("switchImplementationProperty")) {
							switchImplementationProperty = attribute.getValue().getValue().toString();
						}
					}
					JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(
							"com.openi40.scheduler.engine.contextualplugarch.generated." + serviceName);
					PrintWriter out = new PrintWriter(builderFile.openWriter());

					createServiceCode(serviceName, dataType, businessIfaceName, factoryName, implementingClass, key,
							switchImplementationProperty, false, out);
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public boolean process(Set<? extends TypeElement> processed, RoundEnvironment environment) {
		Set<? extends Element> defaultAnnotated = environment.getElementsAnnotatedWith(DefaultImplementation.class);
		Set<? extends Element> alternativeAnnotated = environment
				.getElementsAnnotatedWith(AlternativeImplementation.class);
		for (Element element : defaultAnnotated) {
			if (element instanceof TypeElement) {
				implementThis((TypeElement) element, true);
			}
		}
		for (Element element : alternativeAnnotated) {
			if (element instanceof TypeElement) {
				implementThis((TypeElement) element, false);
			}
		}
		return false;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> outSet = new HashSet<>();
		outSet.add(DefaultImplementation.class.getCanonicalName());
		outSet.add(AlternativeImplementation.class.getCanonicalName());
		return outSet;
	}
}
