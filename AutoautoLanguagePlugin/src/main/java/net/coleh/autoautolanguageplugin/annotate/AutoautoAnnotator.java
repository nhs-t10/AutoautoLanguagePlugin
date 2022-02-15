package net.coleh.autoautolanguageplugin.annotate;

import com.android.tools.r8.position.TextRange;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

import net.coleh.autoautolanguageplugin.AutoautoUtil;
import net.coleh.autoautolanguageplugin.parse.AutoautoFrontMatter;
import net.coleh.autoautolanguageplugin.parse.AutoautoFrontMatterKeyValue;
import net.coleh.autoautolanguageplugin.parse.AutoautoGotoStatement;
import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl;
import net.coleh.autoautolanguageplugin.parse.AutoautoUnitValue;
import net.coleh.autoautolanguageplugin.parse.AutoautoVariableReference;
import net.coleh.autoautolanguageplugin.parse.syntaxhighlight.AutoautoSyntaxHighlighter;
import net.coleh.autoautolanguageplugin.quickfixes.NonexistentStatepathQuickFix;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AutoautoAnnotator implements Annotator {

    public static final String PREFIX_STR = "autoauto:";

    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if(element instanceof AutoautoGotoStatement) {

            AutoautoVariableReference gotoStatepath = ((AutoautoGotoStatement) element).getVariableReference();

            if(gotoStatepath != null) {
                PsiReference ref = gotoStatepath.getReference();
                boolean pathExists = ref != null && ref.resolve() != null;

                if (!pathExists) {
                    String name = gotoStatepath.getName();
                    holder.newAnnotation(HighlightSeverity.ERROR, "Unknown statepath!")
                            .range(element)
                            .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                            .withFix(new NonexistentStatepathQuickFix(name))
                            .create();
                }

                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(gotoStatepath)
                    .textAttributes(AutoautoSyntaxHighlighter.LABEL)
                    .create();
            }
        }
        if(element instanceof AutoautoVariableReference) {
            AutoautoVariableReference variableReference = (AutoautoVariableReference)element;
            if(variableReference.getBaseExpressionType() == AutoautoPsiUtilImpl.BaseExpressionType.FUNCTION_CALL) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(variableReference)
                    .textAttributes(AutoautoSyntaxHighlighter.FUNCTION)
                    .create();
            }
        } if(element instanceof AutoautoFrontMatterKeyValue) {
            AutoautoFrontMatterKeyValue frontMatterKeyValue = (AutoautoFrontMatterKeyValue) element;
            boolean hasUnitvalue = includesUnitValueChild(frontMatterKeyValue.getValue());
            if(hasUnitvalue) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Usage of unit values in frontmatter is not supported")
                        .range(frontMatterKeyValue.getValue())
                        .highlightType(ProblemHighlightType.ERROR)
                        .create();
            }
        } else if(element instanceof AutoautoFrontMatter) {
            AutoautoFrontMatter frontmatter = (AutoautoFrontMatter) element;
            List<AutoautoFrontMatterKeyValue> KVs = frontmatter.getFrontMatterKeyValueList();
            ArrayList<String> names = new ArrayList<String>();

            for(int i = KVs.size() - 1; i >= 0; i--) {
                String text = KVs.get(i).getText();
                int colonIndex = text.indexOf(":");
                if(colonIndex == -1) continue;
                String name = text.substring(0, colonIndex).trim();
                if(names.contains(name)) {
                    holder.newAnnotation(HighlightSeverity.WARNING, "Duplicate key in frontmatter-- only last value will be preserved")
                            .range(KVs.get(i))
                            .highlightType(ProblemHighlightType.WARNING)
                            .create();
                } else {
                    names.add(name);
                }
            }
        }
    }
    private boolean includesUnitValueChild(PsiElement parent) {
        for(PsiElement child : parent.getChildren()) {
            if(child instanceof AutoautoUnitValue) return true;
            else return includesUnitValueChild(child);
        }
        return false;
    }
}
