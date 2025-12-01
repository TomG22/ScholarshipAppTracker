# Java compiler and flags
JAVAC = javac
JAVA  = java

# Source and build folders
SRC_MAIN = src/main/java
SRC_TEST = src/test/java
BUILD = build

# External JARs (if any)
LIBS = lib
CLASSPATH = $(BUILD):$(LIBS)/*

# All Java source files
SRC_MAIN_FILES = $(wildcard $(SRC_MAIN)/*.java)
SRC_TEST_FILES = $(wildcard $(SRC_TEST)/*.java)

# Default target
all: build-tests

# Compile main source files
build: $(BUILD)/.compiled_main

$(BUILD)/.compiled_main: $(SRC_MAIN_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD) $(SRC_MAIN_FILES)
	touch $(BUILD)/.compiled_main

# Compile test files
build-tests: build $(BUILD)/.compiled_tests

$(BUILD)/.compiled_tests: $(SRC_TEST_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD) $(SRC_TEST_FILES)
	touch $(BUILD)/.compiled_tests

# Clean build folder
clean:
	rm -rf $(BUILD)

.PHONY: all build build-tests clean
