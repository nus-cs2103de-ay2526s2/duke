# [02-03-2026]

## A-Personality:

To personalise the chatbot to be more medieval-themed, I used the [Ye Olde English translator](https://anythingtranslate.com/translators/ye-olde-english-translator/)
to convert current text into medieval-themed English. This translator claims to be AI-Powered.

## A-AiAssisted and A-MoreTesting:

I used Junie to create more JUnit tests for later additions to the chatbot, including classes like Storage and Ui to
increase test coverage. After which, I required Junie to ensure that the generated code was up to Java Coding Standards
and the built-in checkstyle rules.

As part of the previous increment of A-UnitTesting, I had spent close to a full day generating test cases. With Junie,
I was able to generate a similar number of test cases in less than 10 minutes.

While most of the generated tests were done in adherence to the standards imposed, I had to manually fix some wildcard
imports and general import order.
