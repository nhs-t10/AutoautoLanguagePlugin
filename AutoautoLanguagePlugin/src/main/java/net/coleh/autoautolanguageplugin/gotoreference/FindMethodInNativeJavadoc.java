package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.openapi.util.Key;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;

import net.coleh.autoautolanguageplugin.NotificationShowerHelper;

public class FindMethodInNativeJavadoc {

    private static final Key<Object> NAME_ON_ANNO_KEY = Key.create("FindMethodInNativeJavadoc Key");

    public static PsiNamedElement find(Project project, String name) {
        PsiClass[] containers = PsiShortNamesCache.getInstance(project).getClassesByName("UselessClassForNativeJavadoc", GlobalSearchScope.projectScope(project));

        PsiClass containerClass = null;

        if(containers.length == 1) containerClass = containers[0];
        else if(containers.length > 1) containerClass = containers[containers.length - 1];

        if(containerClass == null) return null;

        PsiMethod[] methods = containerClass.getMethods();

        for(PsiMethod m : methods) {
            PsiAnnotation[] annos = m.getAnnotations();
            for(PsiAnnotation a : annos) {
                if(a.getQualifiedName().endsWith("AutoautoNativeJavadoc")) {
                    String val = getAttrValString(a.findAttributeValue("name"));

                    if(val.equals(name)) {
                        return m;
                    }
                }
            }
        }
        return null;
    }

    private static String getAttrValString(PsiAnnotationMemberValue attrVal) {
        if(attrVal == null) return "";

        Object cached = attrVal.getCopyableUserData(NAME_ON_ANNO_KEY);
        if(cached != null) return cached.toString();

        String wrappedStr = attrVal.getText();
        String unwrappedStr = wrappedStr.replaceAll("[^\\w.]", "");

        attrVal.putCopyableUserData(NAME_ON_ANNO_KEY, unwrappedStr);
        return unwrappedStr;
    }
}
