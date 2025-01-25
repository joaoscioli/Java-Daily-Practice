# Test Cases

## Overview
This document lists all test cases for the application, organized by feature and layer.

### Table of Contents
1. [Controller Tests](#controller-tests)
2. [Service Tests](#service-tests)
3. [Repository Tests](#repository-tests)

---

## Controller Tests

| Test Case ID | Method                | Input Parameters   | Expected Output                                      | Result |
|--------------|-----------------------|--------------------|----------------------------------------------------|--------|
| TC001        | `getAllStudents()`    | None               | List of all students (2 records)                  | PASS   |

## Service Tests

| Test Case ID | Method                | Input Parameters   | Expected Output                                      | Result |
|--------------|-----------------------|--------------------|----------------------------------------------------|--------|
| TC002        | `findById()`          | Valid ID (1L)      | Student with ID 1 is returned                     | PASS   |

## Repository Tests

| Test Case ID | Method                | Input Parameters   | Expected Output                                      | Result |
|--------------|-----------------------|--------------------|----------------------------------------------------|--------|
| TC003        | `findById()`          | Valid ID (1L)      | Correct student is retrieved from the database     | PASS   |
