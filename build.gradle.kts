// ============================================================
// JavaFX 用の build.gradle.kts（置き換え用）  2026年9月生 Java 講義
//
// IntelliJ の JavaFX のひな形で作ったプロジェクトは、そのままだと
// Java 25 で「互換性のない Gradle JVM」などのエラーになるため、
// プロジェクトの build.gradle.kts の中身を、このファイルの中身で丸ごと置き換える。
//
// ★ 2か所だけ自分のプロジェクトに合わせて直す（下の ★ の行）
//    src/main/java/module-info.java の1行目「module ○○ {」の ○○ を入れる
//    例：プロジェクト名 hello-javafx、Group com.example → com.example.hellojavafx
//
// 置き換えたら：gradle/wrapper/gradle-wrapper.properties の gradle-8.x を
//              gradle-9.1.0 以上にする（IntelliJ のエラー画面のリンクからでもよい）
//              → 右上の象のマークで再読み込み → HelloApplication を ▶ で実行
// ============================================================

plugins {
    java
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("org.example.todoapp")                      // ★ プロジェクト名
    mainClass.set("org.example.todoapp.HelloApplication")      // ★ プロジェクト 名 + メインのクラス名
}

// JavaFX（Windows 用）。授業の PC はすべて Windows のため "win" を指定
val javafxVersion = "25.0.1"
dependencies {
    for (m in listOf("base", "graphics", "controls", "fxml")) {
        implementation("org.openjfx:javafx-$m:$javafxVersion:win")
    }
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-native-access=javafx.graphics")
}