// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.coleh.autoautolanguageplugin.parse.AutoautoTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.coleh.autoautolanguageplugin.parse.*;

public class AutoautoBaseExpressionImpl extends ASTWrapperPsiElement implements AutoautoBaseExpression {

  public AutoautoBaseExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AutoautoVisitor visitor) {
    visitor.visitBaseExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AutoautoVisitor) accept((AutoautoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public AutoautoAtom getAtom() {
    return findNotNullChildByClass(AutoautoAtom.class);
  }

  @Override
  @NotNull
  public List<AutoautoTail> getTailList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AutoautoTail.class);
  }

}
