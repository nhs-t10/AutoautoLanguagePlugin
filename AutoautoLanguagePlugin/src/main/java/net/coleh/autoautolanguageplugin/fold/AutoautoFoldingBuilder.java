package net.coleh.autoautolanguageplugin.fold;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

import net.coleh.autoautolanguageplugin.annotate.AutoautoAnnotator;
import net.coleh.autoautolanguageplugin.parse.AutoautoFrontMatter;
import net.coleh.autoautolanguageplugin.parse.AutoautoLabeledStatepath;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class AutoautoFoldingBuilder implements FoldingBuilder, DumbAware {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode root, @NotNull Document document) {
        PsiElement rootNode = root.getPsi();

        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();

        Collection<PsiElement> statepaths =
                PsiTreeUtil.findChildrenOfAnyType(rootNode, false, AutoautoLabeledStatepath.class, AutoautoFrontMatter.class);

        for(PsiElement p : statepaths) {
            descriptors.add(new FoldingDescriptor(p.getNode(), new TextRange(p.getTextRange().getStartOffset(), p.getTextRange().getEndOffset() - 1)));
        }

        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    private static final String ELLIPSES = "\u2026";

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        if(node instanceof AutoautoFrontMatter) return ELLIPSES + "front matter" + ELLIPSES;
        else if(node instanceof AutoautoLabeledStatepath) return ((AutoautoLabeledStatepath)node).getLabel();
        else return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return node instanceof AutoautoFrontMatter;
    }
}
