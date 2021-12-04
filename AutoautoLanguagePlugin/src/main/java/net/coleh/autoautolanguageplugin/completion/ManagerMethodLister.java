package net.coleh.autoautolanguageplugin.completion;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.javadoc.PsiInlineDocTag;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.search.searches.ClassInheritorsSearch;

import net.coleh.autoautolanguageplugin.AutoautoIcons;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ManagerMethodLister {
    public static ArrayList<AutoautoLookupElement> getFunctionAutocompleteList(Project project) {
        PsiClass[] featureManagerClasses = PsiShortNamesCache.getInstance(project).getClassesByName("FeatureManager", GlobalSearchScope.projectScope(project));

        PsiClass featureManager = null;
        if(featureManagerClasses.length == 1) featureManager = featureManagerClasses[0];
        else if(featureManagerClasses.length > 1) featureManager = featureManagerClasses[featureManagerClasses.length - 1];

        ArrayList<AutoautoLookupElement> lookupList = new ArrayList<>();
        if(featureManager != null) {
            Collection<PsiClass> managers = ClassInheritorsSearch.search(featureManager).findAll();
            for(PsiClass manager : managers) {
                addLookupElem(manager, lookupList);
            }
        }
        return lookupList;
    }
    public static void addLookupElem(PsiClass managerClass, ArrayList<AutoautoLookupElement> listToAdd) {
        PsiMethod[] allMethods = managerClass.getAllMethods();
        for(PsiMethod m : allMethods) {
            if(isAutoautoCallable(m)) {
                listToAdd.add(makeAutocompleteLookupElement(m));
            }
        }
    }


    private static AutoautoLookupElement makeAutocompleteLookupElement(PsiMethod m) {
        PsiParameter[] parameters = m.getParameterList().getParameters();
        String[] argsStr = new String[parameters.length];
        for(int i = 0; i < argsStr.length; i++) {
            argsStr[i] = parameters[i].getName() + ": " + parameters[i].getType().getPresentableText().toLowerCase();
        }
        PsiType returnType = m.getReturnType();
        String returnTypeStr = "";
        if(returnType != null) returnTypeStr = returnType.getPresentableText();

        return new AutoautoLookupElement(m.getName(), argsStr, returnTypeStr, AutoautoIcons.MANAGER_FUNCTION);
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
