/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package net.coleh.autoautolanguageplugin.parse.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import com.intellij.psi.TokenType;
import net.coleh.autoautolanguageplugin.parse.AutoautoTypes;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>AutoautoLexer.flex</tt>
 */
public class AutoautoLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int IN_COMMENT = 2;
  public static final int IN_STRING = 4;
  public static final int IN_LINE_COMMENT = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [11, 6, 4]
   * Total runtime size is 15360 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>10]<<6)|((ch>>4)&0x3f)]<<4)|(ch&0xf)];
  }

  /* The ZZ_CMAP_Z table has 1088 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\6\15\1\16\23\15"+
    "\1\17\1\15\1\20\1\21\12\15\1\22\10\12\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1"+
    "\32\1\33\1\34\1\35\1\36\2\12\1\15\1\37\3\12\1\40\10\12\1\41\1\42\5\15\1\43"+
    "\1\44\11\12\1\45\2\12\1\46\4\12\1\47\1\50\1\51\1\12\1\52\1\12\1\53\1\54\1"+
    "\55\3\12\51\15\1\56\3\15\1\57\1\60\4\15\1\61\12\12\1\62\u02c1\12\1\63\277"+
    "\12");

  /* The ZZ_CMAP_Y table has 3328 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\1\1\11\1\12\1\13\1\14\1\13\1\14\34"+
    "\13\1\15\1\16\1\17\1\1\7\13\1\20\1\21\1\13\1\22\4\13\1\23\10\13\1\22\12\13"+
    "\1\4\1\13\1\24\1\4\1\13\1\25\1\4\1\13\1\26\1\27\1\13\1\30\1\31\1\1\1\30\4"+
    "\13\1\32\6\13\1\33\1\34\1\35\1\1\3\13\1\36\6\13\1\16\3\13\1\37\2\13\1\40\1"+
    "\1\1\13\1\41\4\1\1\13\1\42\1\1\1\43\1\22\7\13\1\44\1\4\1\33\1\45\1\34\1\46"+
    "\1\47\1\50\1\44\1\16\1\51\1\45\1\34\1\52\1\53\1\54\1\55\1\56\1\57\1\22\1\34"+
    "\1\60\1\61\1\62\1\44\1\63\1\64\1\45\1\34\1\60\1\65\1\66\1\44\1\67\1\70\1\71"+
    "\1\72\1\32\1\73\1\74\1\55\1\1\1\75\1\76\1\34\1\77\1\100\1\101\1\44\1\1\1\75"+
    "\1\76\1\34\1\102\1\100\1\103\1\44\1\104\1\105\1\76\1\13\1\36\1\106\1\107\1"+
    "\44\1\110\1\111\1\112\1\13\1\113\1\114\1\115\1\55\1\116\1\4\2\13\1\30\1\117"+
    "\1\120\2\1\1\121\1\122\1\123\1\124\1\125\1\126\2\1\1\62\1\127\1\120\1\130"+
    "\1\131\1\13\1\132\1\4\1\133\1\131\1\13\1\132\1\134\3\1\4\13\1\120\4\13\1\40"+
    "\2\13\1\135\2\13\1\136\24\13\1\137\1\140\2\13\1\137\2\13\1\141\1\142\1\14"+
    "\3\13\1\142\3\13\1\36\2\1\1\13\1\1\5\13\1\143\1\4\45\13\1\35\1\13\1\144\1"+
    "\30\4\13\1\145\1\146\1\147\1\150\1\13\1\150\1\13\1\151\1\147\1\152\5\13\1"+
    "\153\1\120\1\1\1\154\1\120\5\13\1\25\2\13\1\30\4\13\1\56\1\13\1\117\2\41\1"+
    "\55\1\13\1\40\1\150\2\13\1\41\1\13\2\120\2\1\1\13\1\41\3\13\1\117\1\13\1\35"+
    "\2\120\1\155\1\117\4\1\4\13\1\41\1\120\1\156\1\151\7\13\1\151\3\13\1\25\1"+
    "\77\2\13\1\40\1\146\4\1\1\157\1\13\1\160\17\13\1\161\21\13\1\143\2\13\1\143"+
    "\1\162\1\13\1\40\3\13\1\163\1\164\1\165\1\132\1\164\1\166\1\1\1\167\1\170"+
    "\1\62\1\171\1\1\1\172\1\1\1\132\3\1\2\13\1\62\1\173\1\174\1\175\1\126\1\176"+
    "\1\1\2\13\1\146\62\1\1\55\2\13\1\120\161\1\2\13\1\117\2\13\1\117\10\13\1\177"+
    "\1\151\2\13\1\135\3\13\1\200\1\170\1\13\1\201\4\202\2\13\2\1\1\170\35\1\1"+
    "\203\1\1\1\4\1\204\1\4\4\13\1\205\1\4\4\13\1\136\1\206\1\13\1\40\1\4\4\13"+
    "\1\117\1\1\1\13\1\30\3\1\1\13\40\1\133\13\1\56\4\1\135\13\1\56\2\1\10\13\1"+
    "\132\4\1\2\13\1\40\20\13\1\132\1\13\1\41\1\1\3\13\1\207\7\13\1\16\1\1\1\210"+
    "\1\211\5\13\1\212\1\13\1\117\1\25\3\1\1\210\2\13\1\25\1\1\3\13\1\151\4\13"+
    "\1\56\1\120\1\13\1\213\2\13\1\40\2\13\1\151\1\13\1\132\4\13\1\214\1\120\1"+
    "\13\1\117\3\13\1\201\1\40\1\120\1\13\1\112\4\13\1\31\1\154\1\13\1\215\1\216"+
    "\1\217\1\202\2\13\1\136\1\56\7\13\1\220\1\120\72\13\1\151\1\13\1\221\2\13"+
    "\1\41\20\1\26\13\1\40\6\13\1\120\2\1\1\201\1\222\1\34\1\223\1\224\6\13\1\16"+
    "\1\1\1\225\25\13\1\40\1\1\4\13\1\211\2\13\1\25\2\1\1\41\1\13\1\1\1\13\1\226"+
    "\1\227\2\1\1\133\7\13\1\132\1\1\1\120\1\4\1\230\1\4\1\30\1\55\4\13\1\117\1"+
    "\231\1\232\2\1\1\233\1\13\1\14\1\234\2\40\2\1\7\13\1\30\4\1\3\13\1\150\7\1"+
    "\1\235\10\1\1\13\1\132\3\13\2\62\1\1\2\13\1\1\1\13\1\30\2\13\1\30\1\13\1\40"+
    "\2\13\1\236\1\237\2\1\11\13\1\40\1\120\2\13\1\236\1\13\1\41\2\13\1\25\3\13"+
    "\1\151\11\1\23\13\1\201\1\13\1\56\1\25\11\1\1\240\2\13\1\241\1\13\1\56\1\13"+
    "\1\201\1\13\1\117\4\1\1\13\1\242\1\13\1\56\1\13\1\120\4\1\3\13\1\243\4\1\1"+
    "\244\1\245\1\13\1\246\2\1\1\13\1\132\1\13\1\132\2\1\1\131\1\13\1\201\1\1\3"+
    "\13\1\56\1\13\1\56\1\13\1\31\1\13\1\16\6\1\4\13\1\146\3\1\3\13\1\31\3\13\1"+
    "\31\60\1\4\13\1\201\1\1\1\55\1\170\3\13\1\30\1\1\1\13\1\146\1\120\3\13\1\133"+
    "\1\1\2\13\1\247\4\13\1\250\1\251\2\1\1\13\1\22\1\13\1\252\4\1\1\253\1\26\1"+
    "\146\3\13\1\30\1\120\1\33\1\45\1\34\1\60\1\65\1\254\1\255\1\150\10\1\4\13"+
    "\1\30\1\120\2\1\4\13\1\256\1\120\12\1\3\13\1\257\1\62\1\260\2\1\4\13\1\261"+
    "\1\120\2\1\3\13\1\25\1\120\3\1\1\13\1\77\1\41\1\120\26\1\4\13\1\120\1\170"+
    "\34\1\3\13\1\146\20\1\1\34\2\13\1\14\1\62\1\120\1\1\1\211\1\13\1\211\1\131"+
    "\1\201\64\1\71\13\1\120\6\1\6\13\1\117\1\1\14\13\1\151\53\1\2\13\1\117\75"+
    "\1\44\13\1\201\33\1\43\13\1\146\1\13\1\117\1\120\6\1\1\13\1\40\1\150\3\13"+
    "\1\201\1\151\1\120\1\225\1\262\1\13\67\1\4\13\1\150\2\13\1\117\1\170\1\13"+
    "\4\1\1\62\1\1\76\13\1\132\1\1\57\13\1\31\20\1\1\16\77\1\6\13\1\30\1\132\1"+
    "\146\1\263\114\1\1\264\1\265\1\266\1\1\1\267\11\1\1\270\33\1\5\13\1\133\3"+
    "\13\1\147\1\271\1\272\1\273\3\13\1\274\1\275\1\13\1\276\1\277\1\76\24\13\1"+
    "\257\1\13\1\76\1\136\1\13\1\136\1\13\1\133\1\13\1\133\1\117\1\13\1\117\1\13"+
    "\1\34\1\13\1\34\1\13\1\300\3\13\40\1\3\13\1\221\2\13\1\132\1\301\1\302\1\156"+
    "\1\4\25\1\1\14\1\212\1\303\75\1\14\13\1\150\1\201\2\1\4\13\1\30\1\120\112"+
    "\1\1\273\1\13\1\304\1\305\1\306\1\307\1\310\1\311\1\312\1\41\1\313\1\41\47"+
    "\1\1\13\1\120\1\13\1\120\1\13\1\120\47\1\55\13\1\201\2\1\103\13\1\150\15\13"+
    "\1\40\150\13\1\16\25\1\41\13\1\40\56\1\17\13\41\1");

  /* The ZZ_CMAP_A table has 3264 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\1\1\56\3\1\22\0\1\17\1\42\1\40\1\6\1\51\1\47\2\0\1\33\1\34\1\45\1"+
    "\46\1\11\1\2\1\4\1\44\12\3\1\7\1\10\1\41\1\35\1\43\2\0\32\5\1\52\1\0\1\53"+
    "\1\50\1\5\1\0\1\12\1\5\1\24\1\5\1\15\1\13\1\27\1\21\1\25\1\5\1\37\1\32\1\5"+
    "\1\22\1\26\1\30\1\5\1\16\1\31\1\14\1\23\1\5\1\20\1\36\2\5\1\54\1\0\1\55\7"+
    "\0\1\1\12\0\1\1\11\0\1\5\12\0\1\5\4\0\1\5\5\0\27\5\1\0\12\5\4\0\14\5\16\0"+
    "\5\5\7\0\1\5\1\0\1\5\1\0\5\5\1\0\2\5\2\0\4\5\1\0\1\5\6\0\1\5\1\0\3\5\1\0\1"+
    "\5\1\0\4\5\1\0\23\5\1\0\20\5\2\0\1\5\6\0\10\5\10\0\16\5\1\0\1\5\1\0\2\5\1"+
    "\0\2\5\1\0\1\5\10\0\13\5\5\0\3\5\15\0\12\5\4\0\6\5\1\0\10\5\2\0\12\5\1\0\23"+
    "\5\2\0\14\5\2\0\11\5\4\0\1\5\5\0\16\5\2\0\14\5\4\0\5\5\1\0\10\5\6\0\20\5\2"+
    "\0\13\5\2\0\16\5\1\0\1\5\3\0\4\5\2\0\11\5\2\0\2\5\2\0\4\5\10\0\1\5\4\0\2\5"+
    "\1\0\1\5\1\0\3\5\1\0\6\5\4\0\2\5\1\0\2\5\1\0\2\5\1\0\2\5\2\0\1\5\1\0\5\5\4"+
    "\0\2\5\2\0\3\5\3\0\1\5\7\0\4\5\1\0\1\5\7\0\20\5\13\0\3\5\1\0\11\5\1\0\2\5"+
    "\1\0\2\5\1\0\5\5\2\0\12\5\1\0\3\5\1\0\3\5\2\0\1\5\30\0\1\5\7\0\3\5\1\0\10"+
    "\5\2\0\6\5\2\0\2\5\2\0\3\5\10\0\2\5\4\0\2\5\1\0\1\5\1\0\1\5\20\0\2\5\1\0\6"+
    "\5\3\0\3\5\1\0\4\5\3\0\2\5\1\0\1\5\1\0\2\5\3\0\2\5\3\0\3\5\3\0\5\5\3\0\3\5"+
    "\1\0\4\5\2\0\1\5\6\0\1\5\10\0\4\5\1\0\10\5\1\0\3\5\1\0\30\5\3\0\10\5\1\0\3"+
    "\5\1\0\4\5\7\0\2\5\1\0\3\5\5\0\4\5\1\0\5\5\2\0\4\5\5\0\2\5\7\0\1\5\2\0\2\5"+
    "\16\0\3\5\1\0\10\5\1\0\7\5\1\0\3\5\1\0\5\5\5\0\4\5\7\0\1\5\12\0\6\5\2\0\2"+
    "\5\1\0\22\5\3\0\10\5\1\0\11\5\1\0\1\5\2\0\7\5\3\0\1\5\4\0\6\5\1\0\1\5\1\0"+
    "\10\5\2\0\2\5\14\0\17\5\1\0\12\5\7\0\2\5\1\0\1\5\2\0\2\5\1\0\1\5\2\0\1\5\6"+
    "\0\4\5\1\0\7\5\1\0\3\5\1\0\1\5\1\0\1\5\2\0\2\5\1\0\15\5\1\0\3\5\2\0\5\5\1"+
    "\0\1\5\1\0\6\5\2\0\12\5\2\0\4\5\10\0\2\5\13\0\1\5\1\0\1\5\1\0\1\5\4\0\12\5"+
    "\1\0\24\5\3\0\5\5\1\0\12\5\6\0\1\5\11\0\6\5\1\0\1\5\5\0\1\5\2\0\13\5\1\0\15"+
    "\5\1\0\4\5\2\0\7\5\1\0\1\5\1\0\4\5\2\0\1\5\1\0\4\5\2\0\7\5\1\0\1\5\1\0\4\5"+
    "\2\0\16\5\2\0\6\5\2\0\1\1\32\5\3\0\13\5\7\0\15\5\1\0\7\5\13\0\4\5\14\0\1\5"+
    "\1\0\2\5\14\0\4\5\3\0\1\5\4\0\2\5\15\0\3\5\11\0\1\5\23\0\10\5\1\0\23\5\1\0"+
    "\2\5\6\0\6\5\5\0\15\5\1\0\1\5\1\0\1\5\1\0\1\5\1\0\6\5\1\0\7\5\1\0\1\5\3\0"+
    "\3\5\1\0\7\5\3\0\4\5\2\0\6\5\4\0\13\1\15\0\2\1\5\0\1\1\17\0\1\5\4\0\1\5\12"+
    "\0\1\1\1\0\1\5\15\0\1\5\2\0\1\5\4\0\1\5\2\0\12\5\1\0\1\5\3\0\5\5\6\0\1\5\1"+
    "\0\1\5\1\0\1\5\1\0\4\5\1\0\1\5\5\0\5\5\4\0\1\5\1\0\5\5\6\0\15\5\7\0\10\5\11"+
    "\0\7\5\1\0\7\5\1\0\1\1\4\0\3\5\11\0\5\5\2\0\5\5\3\0\7\5\2\0\2\5\2\0\3\5\5"+
    "\0\16\5\1\0\12\5\1\0\1\5\7\0\11\5\2\0\27\5\2\0\15\5\3\0\1\5\1\0\1\5\2\0\1"+
    "\5\16\0\1\5\2\0\5\5\12\0\6\5\2\0\6\5\2\0\6\5\11\0\13\5\1\0\2\5\2\0\7\5\4\0"+
    "\5\5\3\0\5\5\5\0\12\5\1\0\5\5\1\0\1\5\1\0\2\5\1\0\2\5\1\0\12\5\3\0\15\5\3"+
    "\0\2\5\30\0\16\5\4\0\1\5\2\0\6\5\2\0\6\5\2\0\6\5\2\0\3\5\3\0\14\5\1\0\16\5"+
    "\1\0\2\5\1\0\1\5\15\0\1\5\2\0\4\5\4\0\10\5\1\0\5\5\12\0\6\5\2\0\1\5\1\0\14"+
    "\5\1\0\2\5\3\0\1\5\2\0\4\5\1\0\2\5\12\0\10\5\6\0\6\5\1\0\2\5\5\0\10\5\1\0"+
    "\3\5\1\0\13\5\4\0\3\5\4\0\5\5\2\0\1\5\11\0\5\5\5\0\3\5\3\0\13\5\1\0\1\5\3"+
    "\0\10\5\6\0\1\5\1\0\7\5\1\0\1\5\1\0\4\5\1\0\2\5\6\0\1\5\5\0\7\5\2\0\7\5\3"+
    "\0\6\5\1\0\1\5\10\0\6\5\2\0\10\5\10\0\6\5\2\0\1\5\3\0\1\5\13\0\10\5\5\0\15"+
    "\5\3\0\2\5\6\0\5\5\3\0\6\5\10\0\10\5\2\0\7\5\16\0\4\5\4\0\3\5\15\0\1\5\2\0"+
    "\2\5\2\0\4\5\1\0\14\5\1\0\1\5\1\0\7\5\1\0\21\5\1\0\4\5\2\0\10\5\1\0\7\5\1"+
    "\0\14\5\1\0\4\5\1\0\5\5\1\0\1\5\3\0\11\5\1\0\10\5\2\0\2\5\5\0\1\5\16\0\1\5"+
    "\13\0\2\5\1\0\2\5\1\0\5\5\6\0\2\5\1\0\1\5\2\0\1\5\1\0\12\5\1\0\4\5\1\0\1\5"+
    "\1\0\1\5\6\0\1\5\4\0\1\5\1\0\1\5\1\0\1\5\1\0\3\5\1\0\2\5\1\0\1\5\2\0\1\5\1"+
    "\0\1\5\1\0\1\5\1\0\1\5\1\0\1\5\1\0\2\5\1\0\1\5\2\0\4\5\1\0\7\5\1\0\4\5\1\0"+
    "\4\5\1\0\1\5\1\0\12\5\1\0\5\5\1\0\3\5\1\0\5\5\1\0\5\5");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\5\1\6\1\1"+
    "\1\7\1\10\1\11\14\6\1\12\1\13\1\14\1\15"+
    "\1\16\1\1\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\3\31\1\32\1\33"+
    "\1\34\1\35\1\0\1\36\1\4\1\37\7\6\1\40"+
    "\5\6\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\7\6\1\40\7\6\1\50\1\51\1\52\1\6\1\53"+
    "\2\6\1\54\1\55\1\56\1\6\1\57\1\6\1\60"+
    "\1\6\1\61\1\62\4\6\1\63\1\6\1\64";

  private static int [] zzUnpackAction() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\u011a\0\u0149"+
    "\0\u0178\0\u01a7\0\u01d6\0\274\0\274\0\274\0\u0205\0\u0234"+
    "\0\u0263\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac"+
    "\0\u03db\0\u040a\0\274\0\274\0\u0439\0\274\0\u0468\0\u0497"+
    "\0\u04c6\0\u04f5\0\274\0\274\0\274\0\274\0\274\0\274"+
    "\0\274\0\274\0\274\0\u0524\0\u0553\0\274\0\u0582\0\274"+
    "\0\274\0\353\0\u0178\0\u05b1\0\u05e0\0\u01d6\0\u060f\0\u063e"+
    "\0\u066d\0\u069c\0\u06cb\0\u06fa\0\u0729\0\u0758\0\u0787\0\u07b6"+
    "\0\u07e5\0\u0814\0\u0843\0\274\0\274\0\274\0\274\0\274"+
    "\0\274\0\u0524\0\u0872\0\u08a1\0\u08d0\0\u08ff\0\u092e\0\u095d"+
    "\0\u098c\0\274\0\u09bb\0\u09ea\0\u0a19\0\u0a48\0\u0a77\0\u0aa6"+
    "\0\u0ad5\0\u0b04\0\u01a7\0\u01a7\0\u0b33\0\u01a7\0\u0b62\0\u0b91"+
    "\0\u01a7\0\u01a7\0\274\0\u0bc0\0\u01a7\0\u0bef\0\274\0\u0c1e"+
    "\0\274\0\274\0\u0c4d\0\u0c7c\0\u0cab\0\u0cda\0\u01a7\0\u0d09"+
    "\0\u01a7";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\12\1\6"+
    "\1\23\1\12\1\24\2\12\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\2\12\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\1\50\1\51\1\52\1\53\1\6\45\54\1\55\10\54"+
    "\1\56\40\57\1\60\16\57\56\54\1\61\60\0\1\62"+
    "\15\0\1\62\36\0\1\62\3\0\1\10\1\63\55\0"+
    "\1\10\1\63\1\64\4\0\5\64\1\0\13\64\3\0"+
    "\2\64\22\0\1\65\56\0\1\12\1\0\1\12\4\0"+
    "\5\12\1\0\13\12\3\0\2\12\22\0\1\66\1\0"+
    "\1\66\4\0\5\66\1\0\13\66\3\0\2\66\22\0"+
    "\1\12\1\0\1\12\4\0\1\12\1\67\3\12\1\0"+
    "\13\12\3\0\2\12\22\0\1\12\1\0\1\12\4\0"+
    "\1\70\4\12\1\0\3\12\1\71\7\12\3\0\2\12"+
    "\22\0\1\12\1\0\1\12\4\0\4\12\1\72\1\0"+
    "\13\12\3\0\2\12\22\0\1\12\1\0\1\12\4\0"+
    "\5\12\1\0\12\12\1\73\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\5\12\1\0\1\12\1\74\11\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\3\12"+
    "\1\75\1\12\1\0\13\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\1\12\1\76\3\12\1\0\13\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\2\12"+
    "\1\77\2\12\1\0\13\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\5\12\1\0\6\12\1\100\4\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\1\101"+
    "\4\12\1\0\13\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\0\13\12\3\0\1\12\1\102"+
    "\22\0\1\12\1\0\1\12\4\0\3\12\1\103\1\12"+
    "\1\0\13\12\3\0\2\12\54\0\1\104\56\0\1\105"+
    "\56\0\1\106\56\0\1\107\65\0\1\110\1\111\11\0"+
    "\56\54\1\0\44\54\1\112\11\54\1\0\40\57\1\0"+
    "\16\57\3\0\1\64\1\0\1\64\4\0\5\64\1\0"+
    "\13\64\3\0\2\64\22\0\1\65\1\0\1\64\4\0"+
    "\5\64\1\0\13\64\3\0\2\64\22\0\1\12\1\0"+
    "\1\12\4\0\2\12\1\113\2\12\1\0\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\12\12\1\114\3\0\2\12\22\0\1\12\1\0\1\12"+
    "\4\0\5\12\1\0\2\12\1\115\10\12\3\0\2\12"+
    "\22\0\1\12\1\0\1\12\4\0\5\12\1\0\3\12"+
    "\1\116\7\12\3\0\2\12\22\0\1\12\1\0\1\12"+
    "\4\0\5\12\1\0\11\12\1\117\1\12\3\0\2\12"+
    "\22\0\1\12\1\0\1\12\4\0\3\12\1\120\1\12"+
    "\1\0\13\12\3\0\2\12\22\0\1\12\1\0\1\12"+
    "\4\0\5\12\1\0\13\12\3\0\1\121\1\12\22\0"+
    "\1\12\1\0\1\12\4\0\5\12\1\122\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\1\12\1\123\11\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\2\12\1\124\2\12\1\0\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\11\12\1\125\1\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\0\5\12\1\126\5\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\2\12\1\127"+
    "\2\12\1\0\13\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\3\12\1\130\1\12\1\0\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\11\12\1\131\1\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\0\4\12\1\132\6\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\3\12\1\133"+
    "\1\12\1\0\13\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\3\12\1\134\1\12\1\0\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\2\12\1\135\10\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\2\12\1\136\2\12\1\0\13\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\3\12\1\137"+
    "\1\12\1\0\13\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\0\6\12\1\140\4\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\0"+
    "\11\12\1\141\1\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\0\10\12\1\142\2\12\3\0"+
    "\2\12\22\0\1\12\1\0\1\12\4\0\5\12\1\143"+
    "\13\12\3\0\2\12\22\0\1\12\1\0\1\12\4\0"+
    "\4\12\1\144\1\0\13\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\3\12\1\145\1\12\1\0\13\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\2\12"+
    "\1\146\2\12\1\0\13\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\5\12\1\147\13\12\3\0\2\12"+
    "\22\0\1\12\1\0\1\12\4\0\4\12\1\150\1\0"+
    "\13\12\3\0\2\12\22\0\1\12\1\0\1\12\4\0"+
    "\5\12\1\151\13\12\3\0\2\12\22\0\1\12\1\0"+
    "\1\12\4\0\5\12\1\152\13\12\3\0\2\12\22\0"+
    "\1\12\1\0\1\12\4\0\5\12\1\0\5\12\1\153"+
    "\5\12\3\0\2\12\22\0\1\12\1\0\1\12\4\0"+
    "\5\12\1\0\1\154\12\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\5\12\1\0\6\12\1\155\4\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\5\12"+
    "\1\0\5\12\1\156\5\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\5\12\1\0\2\12\1\157\10\12"+
    "\3\0\2\12\22\0\1\12\1\0\1\12\4\0\5\12"+
    "\1\0\11\12\1\160\1\12\3\0\2\12\22\0\1\12"+
    "\1\0\1\12\4\0\3\12\1\161\1\12\1\0\13\12"+
    "\3\0\2\12\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3384];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\6\1\3\11\14\1\2\11\1\1\1\11"+
    "\4\1\11\11\2\1\1\11\1\1\2\11\1\1\1\0"+
    "\20\1\6\11\10\1\1\11\20\1\1\11\3\1\1\11"+
    "\1\1\2\11\7\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  public AutoautoLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AutoautoLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return TokenType.BAD_CHARACTER;
            } 
            // fall through
          case 53: break;
          case 2: 
            { return TokenType.WHITE_SPACE;
            } 
            // fall through
          case 54: break;
          case 3: 
            { return AutoautoTypes.MINUS;
            } 
            // fall through
          case 55: break;
          case 4: 
            { return AutoautoTypes.NUMERIC_VALUE;
            } 
            // fall through
          case 56: break;
          case 5: 
            { return AutoautoTypes.DOT;
            } 
            // fall through
          case 57: break;
          case 6: 
            { return AutoautoTypes.IDENTIFIER;
            } 
            // fall through
          case 58: break;
          case 7: 
            { return AutoautoTypes.COLON;
            } 
            // fall through
          case 59: break;
          case 8: 
            { return AutoautoTypes.SEMICOLON;
            } 
            // fall through
          case 60: break;
          case 9: 
            { return AutoautoTypes.COMMA;
            } 
            // fall through
          case 61: break;
          case 10: 
            { return AutoautoTypes.OPEN_PAREN;
            } 
            // fall through
          case 62: break;
          case 11: 
            { return AutoautoTypes.CLOSE_PAREN;
            } 
            // fall through
          case 63: break;
          case 12: 
            { return AutoautoTypes.EQUALS;
            } 
            // fall through
          case 64: break;
          case 13: 
            { yybegin(IN_STRING); return AutoautoTypes.QUOTE;
            } 
            // fall through
          case 65: break;
          case 14: 
            { return AutoautoTypes.COMPARE_LT;
            } 
            // fall through
          case 66: break;
          case 15: 
            { return AutoautoTypes.DIVIDE;
            } 
            // fall through
          case 67: break;
          case 16: 
            { return AutoautoTypes.MULTIPLY;
            } 
            // fall through
          case 68: break;
          case 17: 
            { return AutoautoTypes.PLUS;
            } 
            // fall through
          case 69: break;
          case 18: 
            { return AutoautoTypes.MODULUS;
            } 
            // fall through
          case 70: break;
          case 19: 
            { return AutoautoTypes.EXPONENTIATE;
            } 
            // fall through
          case 71: break;
          case 20: 
            { return AutoautoTypes.DOLLAR_SIGN;
            } 
            // fall through
          case 72: break;
          case 21: 
            { return AutoautoTypes.OPEN_SQUARE_BRACKET;
            } 
            // fall through
          case 73: break;
          case 22: 
            { return AutoautoTypes.CLOSE_SQUARE_BRACKET;
            } 
            // fall through
          case 74: break;
          case 23: 
            { return AutoautoTypes.OPEN_CURLY_BRACKET;
            } 
            // fall through
          case 75: break;
          case 24: 
            { return AutoautoTypes.CLOSE_CURLY_BRACKET;
            } 
            // fall through
          case 76: break;
          case 25: 
            { return AutoautoTypes.COMMENT_TEXT;
            } 
            // fall through
          case 77: break;
          case 26: 
            { return AutoautoTypes.NON_QUOTE_CHARACTER;
            } 
            // fall through
          case 78: break;
          case 27: 
            { yybegin(YYINITIAL); return AutoautoTypes.QUOTE;
            } 
            // fall through
          case 79: break;
          case 28: 
            { yybegin(YYINITIAL); return AutoautoTypes.LINE_COMMENT_END;
            } 
            // fall through
          case 80: break;
          case 29: 
            { return AutoautoTypes.WHITESPACE_RANGE;
            } 
            // fall through
          case 81: break;
          case 30: 
            { return AutoautoTypes.NUMERIC_VALUE_WITH_UNIT;
            } 
            // fall through
          case 82: break;
          case 31: 
            { return AutoautoTypes.STATEPATH_LABEL_ID;
            } 
            // fall through
          case 83: break;
          case 32: 
            { return AutoautoTypes.IF;
            } 
            // fall through
          case 84: break;
          case 33: 
            { return AutoautoTypes.COMPARE_EQ;
            } 
            // fall through
          case 85: break;
          case 34: 
            { return AutoautoTypes.COMPARE_LTE;
            } 
            // fall through
          case 86: break;
          case 35: 
            { return AutoautoTypes.COMPARE_NEQ;
            } 
            // fall through
          case 87: break;
          case 36: 
            { return AutoautoTypes.COMPARE_GTE;
            } 
            // fall through
          case 88: break;
          case 37: 
            { yybegin(IN_LINE_COMMENT); return AutoautoTypes.LINE_COMMENT_BEGIN;
            } 
            // fall through
          case 89: break;
          case 38: 
            { yybegin(IN_COMMENT); return AutoautoTypes.COMMENT_BEGIN;
            } 
            // fall through
          case 90: break;
          case 39: 
            { yybegin(YYINITIAL); return AutoautoTypes.COMMENT_END;
            } 
            // fall through
          case 91: break;
          case 40: 
            { return AutoautoTypes.FUNC;
            } 
            // fall through
          case 92: break;
          case 41: 
            { return AutoautoTypes.TRUE;
            } 
            // fall through
          case 93: break;
          case 42: 
            { return AutoautoTypes.ELSE;
            } 
            // fall through
          case 94: break;
          case 43: 
            { return AutoautoTypes.NEXT;
            } 
            // fall through
          case 95: break;
          case 44: 
            { return AutoautoTypes.PASS;
            } 
            // fall through
          case 96: break;
          case 45: 
            { return AutoautoTypes.SKIP;
            } 
            // fall through
          case 97: break;
          case 46: 
            { return AutoautoTypes.LET;
            } 
            // fall through
          case 98: break;
          case 47: 
            { return AutoautoTypes.FALSE;
            } 
            // fall through
          case 99: break;
          case 48: 
            { return AutoautoTypes.WHEN;
            } 
            // fall through
          case 100: break;
          case 49: 
            { return AutoautoTypes.GOTO;
            } 
            // fall through
          case 101: break;
          case 50: 
            { return AutoautoTypes.AFTER;
            } 
            // fall through
          case 102: break;
          case 51: 
            { return AutoautoTypes.FUNCTION;
            } 
            // fall through
          case 103: break;
          case 52: 
            { return AutoautoTypes.OTHERWISE;
            } 
            // fall through
          case 104: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
