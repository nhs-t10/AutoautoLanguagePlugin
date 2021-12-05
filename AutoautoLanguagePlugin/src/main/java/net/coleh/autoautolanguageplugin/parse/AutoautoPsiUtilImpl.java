package net.coleh.autoautolanguageplugin.parse;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;

import net.coleh.autoautolanguageplugin.gotoreference.AutoautoPsiReference;
import net.coleh.autoautolanguageplugin.gotoreference.PsiMakerHelper;
import net.coleh.autoautolanguageplugin.parse.impl.AutoautoVariableReferenceImpl;

import java.util.List;

public class AutoautoPsiUtilImpl {

    public enum BaseExpressionType {
        FUNCTION_CALL,
        ARRAY_GET,
        OBJECT_GET,
        VARIABLE
    }

    public static BaseExpressionType getBaseExpressionType(AutoautoVariableReference atom) {
        PsiElement parent = atom.getParent();

        //if this is the `e` in `e.foobar.bazboo()` or `e` or `e()`
        if(parent instanceof AutoautoAtom) {
            AutoautoBaseExpression baseExpression = (AutoautoBaseExpression)parent.getParent();
            List<AutoautoTail> tails = baseExpression.getTailList();
            if(tails.size() == 0) return BaseExpressionType.VARIABLE;
            else return getBaseExpressionTypeFromTail(tails.get(0));
        }
        //if this is the `a` in `foobaz.a.fef()` or `boof.a()`
        else if(parent instanceof AutoautoPropertyGetTail) {
            return getBaseExpressionTypeFromTail(
                (AutoautoTail) (parent
                    .getParent() //an AutoautoSettableTail
                    .getParent() //an AutoautoTail
                    .getNextSibling()) //either another AutoautoTail or null
            );
        }
        return BaseExpressionType.VARIABLE;
    }

    private static BaseExpressionType getBaseExpressionTypeFromTail(AutoautoTail t) {
        if(t == null) return BaseExpressionType.VARIABLE;

        if(t.getFunctionCallTail() != null) return BaseExpressionType.FUNCTION_CALL;

        if(t.getSettableTail() == null) throw new IllegalArgumentException("Somehow a bad AST got made");
        if(t.getSettableTail().getArrayElementGetTail() != null) return BaseExpressionType.ARRAY_GET;
        if(t.getSettableTail().getPropertyGetTail() != null) return BaseExpressionType.OBJECT_GET;

        return BaseExpressionType.VARIABLE;
    }
    public static String getLabel(AutoautoLabeledStatepath element) {
        ASTNode keyNode = element.getNode().findChildByType(AutoautoTypes.STATEPATH_LABEL_ID);
        if (keyNode != null) {
            String label = keyNode.getText();
            if(label.charAt(0) == '#') label = label.substring(1);
            return label.trim();
        } else {
            return null;
        }
    }

    public static PsiElement setName(AutoautoVariableReference statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getFirstChild().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static PsiElement setName(AutoautoLetStatement statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getNameIdentifier().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static PsiElement setName(AutoautoFuncDefStatement statement, String name) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        statement.getNameIdentifier().replace(PsiMakerHelper.makeIdentifier(statement.getProject(), name));
        return statement;
    }

    public static String getName(AutoautoLetStatement statement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        return statement.getNameIdentifier().getText();
    }

    public static String getName(AutoautoFuncDefStatement statement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        return statement.getNameIdentifier().getText();
    }

    public static String getName(AutoautoVariableReference statement) {
        return statement.getFirstChild().getText();
    }

    public static int getTextOffset(AutoautoLetStatement node) {
        return node.getNameIdentifier().getStartOffsetInParent();
    }

    public static int getTextOffset(AutoautoFuncDefStatement node) {
        return node.getNameIdentifier().getStartOffsetInParent();
    }

    public static PsiReference[] getReferences(AutoautoVariableReferenceImpl node) {
        return new PsiReference[] { getReference(node) };
    }
    public static PsiElement resolve(AutoautoVariableReference node) {
        return (new AutoautoPsiReference(node)).resolve();
    }
    public static PsiReference getReference(AutoautoVariableReferenceImpl node) {
        return node;
    }
    public static PsiElement getNameIdentifier(AutoautoLetStatement letStatement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        PsiElement p = letStatement.getFirstChild().getNextSibling();
        while(p instanceof PsiWhiteSpace) p = p.getNextSibling();
        return p;
    }
    public static PsiElement getNameIdentifier(AutoautoFuncDefStatement letStatement) {
        //have to do this bc `getChildren()` doesn't include leafs ://
        PsiElement p = letStatement.getFirstChild().getNextSibling();
        while(p instanceof PsiWhiteSpace) p = p.getNextSibling();
        return p;
    }
}
