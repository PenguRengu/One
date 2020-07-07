(ns one.java)

(def java {"program _ph=0 _ph=1" "import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
public class _ph=0 {
Random _rand = new Random();
public interface Closure {
public void run();
}
_ph=1
}"
           "main _ph=0name _ph=1" "public static void main(String[] _ph=0name) throws Exception {\n_ph=1}\n"
           ;; Printing and intput
           "println _ph=0" "System.out.println(_ph=0);\n"
           "print _ph=0" "System.out.print(_ph=0);\n"
           "read-input" "(new BufferedReader(new InputStreamReader(System.in))).readLine()"
           ;; Types
           "void" "void"
           "empty" "null"
           "str" "String"
           "int" "int"
           "double" "double"
           "bool" "boolean"
           "closure" "Closure"
           "object" "Object"
           "to-str _ph=0" "String.valueOf(_ph=0)"
           "to-int _ph=0" "Integer.parseInt(_ph=0)"
           "to-double _ph=0" "Double.parseDouble(_ph=0)"
           "to-bool _ph=0" "Boolean.parseBoolean(_ph=0)"
           ;; Variables and constants
           "_ph=0 var _ph=1name _ph=2" "_ph=0 _ph=1name = _ph=2;\n"
           "_ph=0 let _ph=1name _ph=2" "_ph=0 _ph=1name = _ph=2;\n"
           "_ph=0 global-var _ph=1name _ph=2" "public static _ph=0 _ph=1name = _ph=2;\n"
           "_ph=0 global-let _ph=1name _ph=2" "public static final _ph=0 _ph=1name = _ph=2;\n"
           "alter _ph=0name _ph=1" "_ph=0name = _ph=1;\n"
           "$ _ph=0name" "_ph=0name"
           "@ _ph=0name" "_ph=0name"
           ;; Math
           "+ _ph=0 _ph=1" "(_ph=0 + _ph=1)"
           "- _ph=0 _ph=1" "(_ph=0 - _ph=1)"
           "* _ph=0 _ph=1" "(_ph=0 * _ph=1)"
           "/ _ph=0 _ph=1" "(_ph=0 / _ph=1)"
           "sqrt _ph=0" "Math.sqrt(_ph=0)"
           "sin _ph=0" "Math.sin(_ph=0)"
           "cos _ph=0" "Math.cos(_ph=0)"
           "tan _ph=0" "Math.tan(_ph=0)"
           "abs _ph=0" "Math.abs(_ph=0)"
           "rand-int _ph=0" "_rand.nextInt(_ph=0)"
           ;; String
           "strlen _ph=0" "_ph=0.length()"
           "substr _ph=0 _ph=1 _ph=2" "_ph=0.substring(_ph=1, _ph=2)"
           "split _ph=0 _ph=1" "Arrays.asList(_ph=0.split(_ph=1))"
           "split-lines _ph=0" "Arrays.asList(_ph=0.split(\"\\n\"))"
           "trim _ph=0" "_ph=0.trim()"
           "concat _ph=0 _ph=1" "(_ph=0 + _ph=1)"
           ;; Boolean
           "== _ph=0 _ph=1" "(_ph=0 == _ph=1)"
           "= _ph=0 _ph=1" "_ph=0.equals(_ph=1)"
           "> _ph=0 _ph=1" "(_ph=0 > _ph=1)"
           "< _ph=0 _ph=1" "(_ph=0 < _ph=1)"
           ">= _ph=0 _ph=1" "(_ph=0 >= _ph=1)"
           "<= _ph=0 _ph=1" "(_ph=0 <= _ph=1)"
           ;; Control
           "if _ph=0 _ph=1" "if (_ph=0) {\n_ph=1}\n"
           "if _ph=0 _ph=1 else _ph=2" "if (_ph=0) {\n_ph=1} else {\n_ph=2}\n"
           "while _ph=0 _ph=1" "while (_ph=0) {\n_ph=1}\n"
           "loop-times _ph=0name _ph=1 _ph=2" "for (int _ph=0name = 0; _ph=0name < _ph=1; _ph=0name++) {\n_ph=2}\n"
           ;; Functions
           "func _ph=0name _ph=1 _ph=2name -> _ph=3 _ph=4" "public static _ph=3 _ph=0name(_ph=1 _ph=2name) throws Exception {\n_ph=4}\n"
           "return _ph=0" "return _ph=0;\n"
           "call _ph=0name _ph=1" "_ph=0name(_ph=1);\n"
           "call-> _ph=0name _ph=1" "_ph=0name(_ph=1)"
           "closure _ph=0" "new Closure() {\n@Override\npublic void run() {\n_ph=0}\n}"
           "call-cls _ph=0" "_ph=0.run();\n"
           ;; Array
           "int-array" "ArrayList<Integer>"
           "str-array" "ArrayList<String>"
           "bool-array" "ArrayList<Boolean>"
           "double-array" "ArrayList<Double>"
           "closure-array" "ArrayList<Closure>"
           "object-array" "ArrayList<Object>"
           "new-int-array" "new ArrayList<Integer>()"
           "new-str-array" "new ArrayList<String>()"
           "new-bool-array" "new ArrayList<Boolean>()"
           "new-double-array" "new ArrayList<Double>()"
           "new-closure-array" "new ArrayList<Closure>()"
           "new-object-array" "new ArrayList<Object>()"
           "add-element _ph=0 _ph=1" "_ph=0.add(_ph=1);\n"
           "set-element _ph=0 _ph=1 _ph=2" "_ph=0.set(_ph=1, _ph=2);\n"
           "get _ph=0 _ph=1" "_ph=0.get(_ph=1)"
           "count _ph=0" "_ph=0.size()"
           ;; Time
           "wait _ph=0" "try {\nThread.sleep(_ph=0);\n} catch (Exception ex) {}\n"
           "get-time _ph=0name" "long _ph=0name = new Date().getTime();\n"
           ;; Persistence
           "write-key _ph=0 _ph=1" "try {\nBufferedWriter out = new BufferedWriter(new FileWriter(_ph=0));\nout.write(_ph=1);\nout.close();\n} catch (IOException ex) {}\n"
           "read-key _ph=0 _ph=1 into _ph=2name" "_ph=2name = _ph=1;\ntry {\n_ph=2name = new String (Files.readAllBytes(Paths.get (_ph=0)), StandardCharsets.UTF_8);\n} catch (IOException ex) {}\n"})