# AI Tool Declaration

I used **Google Gemini** to assist with the following increments:

* **A-Streams:** Generated code for `TaskList.java` to replace for-loops with Java Streams (`filter`, `map`, `collect`).
* **A-CodeQuality:** Assisted in refactoring `Parser.java` to separate logic into helper methods and generating Javadocs.
* **A-CI:** Provided the YAML configuration script for GitHub Actions (`ci.yml`) and helped debug Java version compatibility.
* **A-BetterGui:** Generated JavaFX code for window resizing constraints (`AnchorPane`) and dynamic error highlighting.
* **A-MoreErrorHandling:** Suggested logic for date validation (`isBefore`) and try-catch blocks for corrupted save files.
* **General Debugging:** Helped interpret Gradle build errors and CheckStyle violations (e.g., line length limits, unused imports).

I am responsible for the content and quality of the submitted work.

## Observations

### What worked well?
* **Boilerplate Code:** The AI was extremely fast at generating verbose code like JavaFX UI components and JUnit test skeletons, saving me hours of typing.
* **Git Workflow:** The AI guided me through complex Git operations (Parallel PRs, resolving merge conflicts, and retagging), which helped me understand the workflow better than reading static guides.
* **Streams API:** Converting loops to Streams was much easier with AI assistance, as the syntax can be tricky for beginners.

### What didn't work?
* **Context Awareness:** The AI sometimes suggested imports (like `javafx.scene.layout.Region`) that were no longer needed after code changes, causing CheckStyle errors.
* **Style Violations:** The generated code occasionally exceeded the 120-character line limit, requiring manual cleanup to pass CI checks.
* **Logical Nuances:** I had to manually adjust the "Time Travel" validation logic because the AI's initial try-catch block inadvertently skipped the logic check for invalid formats.