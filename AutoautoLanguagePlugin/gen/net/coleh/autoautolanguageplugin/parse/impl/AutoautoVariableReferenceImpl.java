// This is a generated file. Not intended for manual editing.
package net.coleh.autoautolanguageplugin.parse.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.coleh.autoautolanguageplugin.parse.AutoautoTypes.*;
import net.coleh.autoautolanguageplugin.gotoreference.AutoautoVariableReferenceImplReferenceBridge;
import net.coleh.autoautolanguageplugin.parse.*;
import com.intellij.psi.PsiReference;
import net.coleh.autoautolanguageplugin.parse.AutoautoPsiUtilImpl.BaseExpressionType;

public class AutoautoVariableReferenceImpl extends AutoautoVariableReferenceImplReferenceBridge implements AutoautoVariableReference {

  public AutoautoVariableReferenceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AutoautoVisitor visitor) {
    visitor.visitVariableReference(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AutoautoVisitor) accept((AutoautoVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public BaseExpressionType getBaseExpressionType() {
    return AutoautoPsiUtilImpl.getBaseExpressionType(this);
  }

  @Override
  public PsiReference getReference() {
    return AutoautoPsiUtilImpl.getReference(this);
  }

  @Override
  public String getName() {
    return AutoautoPsiUtilImpl.getName(this);
  }

  @Override
  public PsiElement setName(String name) {
    return AutoautoPsiUtilImpl.setName(this, name);
  }

  @Override
  public PsiReference[] getReferences() {
    return AutoautoPsiUtilImpl.getReferences(this);
  }

}
