# 📐 StringCalculator

A Java implementation of the classic TDD kata: **String Calculator**.

This project demonstrates best practices in **Test-Driven Development (TDD)**, including:
✅ Red-Green-Refactor cycle  
✅ Writing comprehensive unit tests  
✅ Continuous Integration with GitHub Actions  
✅ Professional documentation

---

## ✨ Features

- **Basic operations**: Add numbers separated by commas or newlines.
- **Custom delimiters**: Support for single, multiple, and multi-character delimiters.
- **Negative numbers**: Throws an exception listing all negatives found.
- **Large numbers**: Ignores numbers greater than 1000.
- **Call counting**: Tracks how many times `add()` was called.

---


## 🧪 Test Coverage

This project includes comprehensive tests verifying all key behaviors:

- ✅ Returns 0 for empty or null strings.
- ✅ Returns the number itself when input has a single number.
- ✅ Returns sum of two comma-separated numbers.
- ✅ Handles any number of comma-separated numbers.
- ✅ Supports mixing newlines and commas as delimiters.
- ✅ Supports custom single-character delimiters.
- ✅ Throws an exception on negative numbers.
- ✅ Throws an exception listing all negative numbers if multiple negatives present.
- ✅ Tracks the number of times `add()` is called via `getCalledCount()`.
- ✅ Ignores numbers larger than 1000.
- ✅ Supports multi-character custom delimiters (e.g., `//[***]\n1***2***3`).
- ✅ Supports multiple single-character custom delimiters (e.g., `//[*][%]\n1*2%3`).
- ✅ Supports multiple multi-character custom delimiters (e.g., `//[**][%%]\n1**2%%3`).


---


## 🚀 Quick Start

### 📦 Requirements

- Java 17 or newer  
- Maven (if building manually)

---

## 🚀 Quick Start

### 📦 Requirements

- Java 17 or newer  
- Maven (if building manually)

---


📝 Notes
This project was developed as part of a TDD exercise, practicing:

- 🔴 RED: Starting with failing (or non-compiling) tests.
- 🟢 GREEN: Implementing the minimal passing code.
- 🟦 REFACTOR: Refactoring for clarity, maintainability, and performance.


🔗 Helpful resources:

📘 GitHub Actions Documentation
📝 The String Calculator Kata


🔗 **Helpful resources:**

- [📘 GitHub Actions Documentation](https://docs.github.com/en/actions)
- [📝 The String Calculator Kata](https://osherove.com/tdd-kata-1)

---

### 🛠️ How to Build & Run Tests
From your project root:
```bash
mvn clean install
mvn test
