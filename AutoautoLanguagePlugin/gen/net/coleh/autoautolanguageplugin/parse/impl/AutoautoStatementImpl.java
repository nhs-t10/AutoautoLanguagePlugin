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

public class AutoautoStatementImpl extends ASTWrapperPsiElement implements AutoautoStatement {

  public AutoautoStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AutoautoVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AutoautoVisitor) accept((AutoautoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public AutoautoMultiStatement getMultiStatement() {
    return findChildByClass(AutoautoMultiStatement.class);
  }

  @Override
  @Nullable
  public AutoautoSingleStatement getSingleStatement() {
    return findChildByClass(AutoautoSingleStatement.class);
  }

}
