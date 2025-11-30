# Java compiler and flags
JAVAC = javac
JAVA  = java

# Paths
SRC_MAIN = src/main/java
SRC_TEST = src/tests/java
BUILD = build
LIBS = lib

# External JARs (semicolon for Windows, colon for Linux/Mac)
CLASSPATH = $(BUILD):$(LIBS)/*

# Packages
PKGS_MAIN = backend
PKGS_TEST = backend

# Java files
SRC_MAIN_FILES = $(foreach pkg,$(PKGS_MAIN),$(wildcard $(SRC_MAIN)/$(pkg)/*.java))
SRC_TEST_FILES = $(foreach pkg,$(PKGS_TEST),$(wildcard $(SRC_TEST)/$(pkg)/*.java))

# Default target
all: build-tests

# Compile main code
build: $(BUILD)/.compiled_main

$(BUILD)/.compiled_main: $(SRC_MAIN_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD) $(SRC_MAIN_FILES)
	touch $(BUILD)/.compiled_main

# Compile test code
build-tests: build $(BUILD)/.compiled_tests

$(BUILD)/.compiled_tests: $(SRC_TEST_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -cp $(CLASSPATH) -d $(BUILD) $(SRC_TEST_FILES)
	touch $(BUILD)/.compiled_tests

# Clean
clean:
	rm -rf $(BUILD)

.PHONY: all build build-tests clean
