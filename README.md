# これは
[JaCoCo](https://www.jacoco.org/jacoco/trunk/index.html) の HTML レポートからカバレッジを収集する． 

# 使い方
## ビルド
[Maven](https://maven.apache.org/) が必要．（Maven 3.9.6 かつ Java 21 で動作を確認済み）
~~~
% mvn clean package
% cp target/coverage_collecter.jar <target project dir>
% cd <target project dir>
~~~

## 使用
対象プロジェクトのカバレッジレポートを JaCoCo でとれるようにする必要あり．  
以下はその前提での使用例．
~~~
% mvn test -Dtest=XTest#test00 jacoco:report
% java -jar coverage_collecter.jar XTest#test00 0 target/site/jacoco output_dir
~~~

### 出力
```<output dir>/<specified test method name>.cov``` が出力される．  
ファイルの形式および具体例は以下の通り．

~~~
0 
xxx.yyy.zzz.A.java:10,11,12,13
xxx.yyy.B.java:100,110,111,112,114,334
xxx.yyy.C.java:
xxx.www.D.java:3,4
...
~~~