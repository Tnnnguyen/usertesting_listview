# usertesting_listview

This mini app has the following requirements:


Create an Android application that makes a network call to the following url, parses the data, and displays it in a list view:

https://s3-us-west-1.amazonaws.com/candidate-test/sample_json

A few things to consider:

Tests that have state:reserved should not be clickable and distinct from other tests

Only display tests that are for the android platform and state:available

Tests that have non-null screeners should display a button that says “Take Screener”. Other tests will show a button that says “Accept”

Tapping Accept show another view that shows the details of the test such as requirements, operating system, (use your best judgement as to what to include)

Tapping Take Screener will show the screener question and a multiple choice list of answers

Decline button removes the test from the list view

Tests should display the type of test and any requirements.
