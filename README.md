# KKui

[![JitPack](https://jitpack.io/v/KPGTB/KKcore.svg)](https://jitpack.io/#KPGTB/KKcore)
![Spigot](https://img.shields.io/badge/Spigot-1.13--1.18-yellow)
![License](https://img.shields.io/badge/License-Apache%202.0-orange)
![Author](https://img.shields.io/badge/Author-KPG--TB-green)
------------------------------------------------------------

This is API for KK plugins. With this API you can add custom UI.

### Supported versions

---

Spigot 1.13 - 1.18

### Maven

---

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.KPGTB</groupId>
    <artifactId>KKui</artifactId>
    <version>LATEST</version>
</dependency>
```

### Usage

---

How to add new element to UI?

```java
    BaseUI baseUI = new BaseUI(String text, Alignment alignment, int offset); // Create new BaseUI object
    KKui.getUiManager().addUI(UUID uuid, BaseUI baseUI); // Register BaseUI object
```

How to update element?
```java
    baseUI.update(String text);
```

How to remove element?
```java
    KKui.getUiManager().removeUI(UUID uuid, BaseUI baseUI)
```

### Licence

---

Apache 2.0
