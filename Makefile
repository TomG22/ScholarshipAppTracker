# Java compiler and flags
JAVAC = javac
JAVA  = java

# Source directories
SRC_MAIN = src/main/java
SRC_TEST = src/tests/java

# Packages to compile in main source
PKGS_MAIN = backend matchingengine

# Packages to compile in test source
PKGS_TEST = backend

# Build directory
BUILD = build

# Collect all Java files from main packages
SRC_MAIN_FILES = $(foreach pkg,$(PKGS_MAIN),$(wildcard $(SRC_MAIN)/$(pkg)/*.java))

# Collect all Java files from test packages
SRC_TEST_FILES = $(foreach pkg,$(PKGS_TEST),$(wildcard $(SRC_TEST)/$(pkg)/*.java))

# Default target: build all main and test code
all: build-tests

# Compile main sources
build:
	$(BUILD)/.compiled_main

# Compile main sources
$(BUILD)/.compiled_main: $(SRC_MAIN_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -d $(BUILD) $(SRC_MAIN_FILES)
	touch $(BUILD)/.compiled_main

# Compile test sources
build-tests: build $(BUILD)/.compiled_tests

$(BUILD)/.compiled_tests: $(SRC_TEST_FILES)
	mkdir -p $(BUILD)
	$(JAVAC) -d $(BUILD) -cp $(BUILD) $(SRC_TEST_FILES)
	touch $(BUILD)/.compiled_tests

# Clean everything
clean:
	rm -rf $(BUILD)

.PHONY: build build-tests clean

