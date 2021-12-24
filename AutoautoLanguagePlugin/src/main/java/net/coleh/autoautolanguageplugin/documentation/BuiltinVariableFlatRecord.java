package net.coleh.autoautolanguageplugin.documentation;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.impl.PsiElementBase;
import com.intellij.util.IncorrectOperationException;

import net.coleh.autoautolanguageplugin.AutoautoIcons;
import net.coleh.autoautolanguageplugin.AutoautoLanguage;
import net.coleh.autoautolanguageplugin.completion.AutoautoLookupElement;
import net.coleh.autoautolanguageplugin.gotoreference.FindMethodInNativeJavadoc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuiltinVariableFlatRecord {
    public static BuiltinVariableFlatRecord[] builtins = {

        new BuiltinVariableFlatRecord("", "return", "Return a value from a function", new String[] {"v"}, null),
        new BuiltinVariableFlatRecord("", "typeof", "Get the type of a value, as a string", new String[] {"v"}, "string"),
        new BuiltinVariableFlatRecord("", "log", "Log the arguments to the robot's telemetry screen", new String[] {"s..."}, "void"),

        new BuiltinVariableFlatRecord("", "delegate", "Delegate a loop to another module. Returns whatever the last value they `provide()`d was.", new String[] {"moduleName"}, "*"),
        new BuiltinVariableFlatRecord("", "provide", "Provide a value to a parent module.", new String[] {"value"}, "void"),


        new BuiltinVariableFlatRecord("", "Math", "built-in Table for math functions-- identical to Javascript's built-in Math object.", null, "table"),
        new BuiltinVariableFlatRecord("Math", "E","Euler's constant and the base of natural logarithms; approximately 2.718.", null, "=" + Math.E),
        new BuiltinVariableFlatRecord("Math", "LN2","Natural logarithm of 2; approximately 0.693.", null, "=" +Math.log(2)),
        new BuiltinVariableFlatRecord("Math", "LN10","Natural logarithm of 10; approximately 2.303.", null, "=" + Math.log(10)),
        new BuiltinVariableFlatRecord("Math", "LOG2E","Base-2 logarithm of E; approximately 1.443.", null, "=1.4426950408889634"),
        new BuiltinVariableFlatRecord("Math", "LOG10E","Base-10 logarithm of E; approximately 0.434.", null, "=" + Math.log10(Math.E)),
        new BuiltinVariableFlatRecord("Math", "PI","Ratio of a circle's circumference to its diameter; approximately 3.14159.", null, "=" + Math.PI),
        new BuiltinVariableFlatRecord("Math", "SQRT1_2","Square root of ½; approximately 0.707.", null, "=" + Math.sqrt(0.5)),
        new BuiltinVariableFlatRecord("Math", "SQRT2","Square root of 2; approximately 1.414.", null, "=" + Math.sqrt(2)),
        new BuiltinVariableFlatRecord("Math", "abs","Returns the absolute value of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "acos","Returns the arccosine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "acosh","Returns the hyperbolic arccosine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "asin","Returns the arcsine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "asinh","Returns the hyperbolic arcsine of a number.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "atan","Returns the arctangent of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "atanh","Returns the hyperbolic arctangent of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "atan2","Returns the arctangent of the quotient of its arguments.", new String[] {"y","x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "cbrt","Returns the cube root of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "ceil","Returns the smallest integer greater than or equal to x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "clz32","Returns the number of leading zero bits of the 32-bit integer x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "cos","Returns the cosine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "cosh","Returns the hyperbolic cosine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "exp","Returns e^x, where x is the argument, and e is Euler's constant (2.718…, the base of the natural logarithm).", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "expm1","Returns subtracting 1 from exp(x).", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "floor","Returns the largest integer less than or equal to x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "fround","Returns the nearest single precision float representation of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "hypot","Returns the square root of the sum of squares of its arguments.", new String[] {"x","y","..."}, "number"),
        new BuiltinVariableFlatRecord("Math", "imul","Returns the result of the 32-bit integer multiplication of x and y.", new String[] {"x","y"}, "number"),
        new BuiltinVariableFlatRecord("Math", "log","Returns the natural logarithm (㏒e; also, ㏑) of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "log1p","Returns the natural logarithm (㏒e; also ㏑) of 1 + x for the number x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "log10","Returns the base-10 logarithm of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "log2","Returns the base-2 logarithm of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "max","Returns the largest of zero or more numbers.", new String[] {"x","y","..."}, "number"),
        new BuiltinVariableFlatRecord("Math", "min","Returns the smallest of zero or more numbers.", new String[] {"x","y","..."}, "number"),
        new BuiltinVariableFlatRecord("Math", "pow","Returns base x to the exponent power y (that is, x^y).", new String[] {"x","y"}, "number"),
        new BuiltinVariableFlatRecord("Math", "random","Returns a pseudo-random number between 0 and 1.", new String[] {""}, "number"),
        new BuiltinVariableFlatRecord("Math", "round","Returns the value of the number x rounded to the nearest integer.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "sign","Returns the sign of the x, indicating whether x is positive, negative, or zero.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "sin","Returns the sine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "sinh","Returns the hyperbolic sine of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "sqrt","Returns the positive square root of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "tan","Returns the tangent of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "tanh","Returns the hyperbolic tangent of x.", new String[] {"x"}, "number"),
        new BuiltinVariableFlatRecord("Math", "trunc","Returns the integer portion of x, removing any fractional digits.", new String[] {"x"}, "number")
    };

    private final String name, doc, varContext, returnType;
    private final String[] args;

    public BuiltinVariableFlatRecord(String varContext, String name, String doc, String[] args, String returnType) {
        this.varContext = varContext;
        this.name = name;
        this.doc = doc;
        this.args = args;
        this.returnType = returnType;
    }

    public static void addBuiltIns(String context, CompletionResultSet resultSet, Project project) {
        //add built-ins!
        for(BuiltinVariableFlatRecord b : BuiltinVariableFlatRecord.builtins) {
            if(b.getVarContext().equals(context)) {
                resultSet.addElement(
                        new AutoautoLookupElement(b.name,
                                b.args,
                                b.returnType,
                                AutoautoIcons.BUILTIN, "",
                                FindMethodInNativeJavadoc.find(project, b.name))
                );
            }
        }
    }

    public String getDoc() {
        return doc;
    }
    public String getName() {
        return name;
    }
    public String getVarContext() {
        return varContext;
    }

}
