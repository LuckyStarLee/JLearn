#learnjava
java学习笔记<br>
项目添加mvnw支持
#####表示我们期望使用的maven的版本为3.3.9<br>
mvn -N io.takari:maven:wrapper -Dmaven=3.3.9<br>
注意:<br>
由于我们使用了新的maven ,如果你的settings.xml没有放在当前用户下的.m2目录下，<br>
那么执行mvnw时不会去读取你原来的settings.xml文件<br>
#####maven wrapper使用说明<br>
1.父项目下执行maven命令<br>
./mvnw clean<br>
2.各个子模块下指定pom<br>
如:<br>
./mvnw clean -f learn-pattern/pom.xml
