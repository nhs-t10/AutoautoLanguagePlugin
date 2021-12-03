package net.coleh.autoautolanguageplugin.parse;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

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
}
