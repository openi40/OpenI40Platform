package com.openi40.scheduler.engine.contextualplugarch;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.AnnotationFormatError;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

import org.kohsuke.MetaInfServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = { "com.openi40.scheduler.engine.contextualplugarch.BusinessInterface" })
@MetaInfServices(value = javax.annotation.processing.Processor.class)
public class BusinessInterfaceAnnotationProcessor extends AbstractProcessor {

	public BusinessInterfaceAnnotationProcessor() {

	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		System.out.println(getClass().getName() + ".init() called!!");
	}

	String getCompleteName(Element element) {
		String _sn = "";
		do {
			_sn += element.getSimpleName().toString();
			element = element.getEnclosingElement();
		} while (element != null);
		return _sn;
	}

	@Override
	public boolean process(Set<? extends TypeElement> supportedAnnotation, RoundEnvironment environment) {
		System.out.println(getClass().getName() + ".process() called!! ");
		Set<? extends Element> annotated = environment.getElementsAnnotatedWith(BusinessInterface.class);
		for (Element element : annotated) {
			if (element instanceof TypeElement) {
				javax.lang.model.element.TypeElement typeElement = (TypeElement) element;
				TypeMirror mirror = element.asType();
				if (mirror.getKind() == TypeKind.DECLARED) {
					String completeName = typeElement.getQualifiedName().toString();
					String simpleName = typeElement.getSimpleName().toString();
					String builderFactoryName = simpleName + "Factory";
					String targetFactoryName = "com.openi40.scheduler.engine.contextualplugarch.generated."
							+ builderFactoryName;
					String builderClassName = simpleName.substring(1) + "ImplementationChooser";
					List<? extends AnnotationMirror> amirrors = element.getAnnotationMirrors();
					String _entityClass = null;
					for (AnnotationMirror amirror : amirrors) {

						Map<? extends ExecutableElement, ? extends AnnotationValue> annotationValues = amirror
								.getElementValues();
						for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationValues
								.entrySet()) {
							if (entry.getKey().getSimpleName().toString().equals("entityClass")) {
								Object value = entry.getValue();
								Object entityClass = entry.getValue();
								if (entityClass instanceof Class) {
									_entityClass = ((Class) entityClass).getName();
								} else {
									_entityClass = entityClass.toString();
									_entityClass = _entityClass.replace(".class", "");
								}
							}
						}
					}
					try {
						String factoryType = "IBusinessLogicFactory<" + completeName + ", " + _entityClass + ">";
						JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(targetFactoryName);
						PrintWriter out = new PrintWriter(builderFile.openWriter());
						out.println("package com.openi40.scheduler.engine.contextualplugarch.generated;");
						out.println("import com.openi40.scheduler.engine.contextualplugarch.*;");
						out.println("public interface " + builderFactoryName + " extends " + factoryType + "{}");
						out.close();
						builderFile = processingEnv.getFiler().createSourceFile(
								"com.openi40.scheduler.engine.contextualplugarch.generated." + builderClassName);
						out = new PrintWriter(builderFile.openWriter());
						out.println("package com.openi40.scheduler.engine.contextualplugarch.generated;");
						out.println("import com.openi40.scheduler.engine.contextualplugarch.*;");
						out.println("import org.springframework.context.annotation.*;");
						out.println("import org.springframework.beans.factory.annotation.*;");
						out.println("import org.springframework.stereotype.*;");
						out.println("@" + Service.class.getSimpleName());
						
						String typeString = AbstractBusinessLogicImplementationChooser.class.getSimpleName() + "<"
								+ completeName + "," + builderFactoryName + "," + _entityClass + "> implements "
								+ IBusinessLogicImplementationChooser.class.getName() + "<" + completeName + ", "
								+ builderFactoryName + "," + _entityClass + ">";
						out.println("public class " + builderClassName + " extends " + typeString + " {");
						out.println("public " + builderClassName + "(@Autowired @" + Qualifier.class.getSimpleName()
								+ "(\"defaultFactory\") " + builderFactoryName + " defaultFactory){");
						out.println("	super(" + completeName + ".class,defaultFactory);");
						out.println("}");
						out.println("}");
						out.flush();
						out.close();

					} catch (IOException exception) {
						exception.printStackTrace();
						throw new AnnotationFormatError("Cannot generate file", exception);
					}
				} else
					throw new AnnotationFormatError("The annotation BusinessInterface must be on interface type");
			}
		}
		return false;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> _annoTypes = new HashSet<>();
		_annoTypes.add(BusinessInterface.class.getCanonicalName());
		return _annoTypes;
	}
}
