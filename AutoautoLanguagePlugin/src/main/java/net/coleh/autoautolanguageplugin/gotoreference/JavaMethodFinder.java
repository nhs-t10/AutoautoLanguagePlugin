package net.coleh.autoautolanguageplugin.gotoreference;

import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.search.searches.ClassInheritorsSearch;

import net.coleh.autoautolanguageplugin.completion.AutoautoLookupElement;
import net.coleh.autoautolanguageplugin.documentation.JavadocTagHtmlTranslator;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;

public class JavaMethodFinder {
    public static PsiNamedElement getByName(Project project, String name) {
        PsiClass[] featureManagerClasses = PsiShortNamesCache.getInstance(project).getClassesByName("FeatureManager", GlobalSearchScope.projectScope(project));

        PsiClass featureManager = null;
        if(featureManagerClasses.length == 1) featureManager = featureManagerClasses[0];
        else if(featureManagerClasses.length > 1) featureManager = featureManagerClasses[featureManagerClasses.length - 1];

        if(featureManager != null) {
            Collection<PsiClass> managers = ClassInheritorsSearch.search(featureManager).findAll();
            for(PsiClass manager : managers) {
                PsiMethod[] allMethods = manager.getAllMethods();
                for(PsiMethod m : allMethods) {
                    if(isAutoautoCallable(m) && name.equals(m.getName())) {
                        return m;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isAutoautoCallable(PsiMethod m) {
        if(m.isConstructor()) return false;

        PsiParameter[] parameters = m.getParameterList().getParameters();

        boolean hasGoodParams = true;
        for(PsiParameter p : parameters) {
            if(!isStringOrPrimitive(p.getType())) {
                hasGoodParams = false;
                break;
            }
        }

        return hasGoodParams && isStringOrPrimitive(m.getReturnType());
    }

    public static boolean isStringOrPrimitive(@Nullable PsiType type) {
        if(type == null) return true;

        PsiType rootType = type.getDeepComponentType();

        String cannonicalTypeText = rootType.getCanonicalText();

        return cannonicalTypeText.equals("void") ||
                cannonicalTypeText.equals("byte") ||
                cannonicalTypeText.equals("short") ||
                cannonicalTypeText.equals("int") ||
                cannonicalTypeText.equals("long") ||
                cannonicalTypeText.equals("float") ||
                cannonicalTypeText.equals("double") ||
                cannonicalTypeText.equals("boolean") ||
                cannonicalTypeText.equals("char") ||
                cannonicalTypeText.equals("java.lang.String");
    }
}
