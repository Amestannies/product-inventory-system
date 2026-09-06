# Product Inventory Management System

A Java-based inventory management system supporting both CSV and JSON formats, built with a Factory design pattern for extensible file I/O and covered by JUnit 5 tests.

<img width="623" height="692" alt="product_inventory_example" src="https://github.com/user-attachments/assets/cba9ca37-d6aa-4586-a2ae-dfe37627add5" />

## Requirements
- Java 21+
- Dependencies (included in `/lib`): opencsv 5.7.1, commons-lang3 3.12.0, json-simple 1.1.1

## Tech Stack
Java · JUnit 5 · Factory Design Pattern · opencsv · JSON-simple

## What This Demonstrates
- **Factory design pattern** (`ProductReaderFactory` / `ProductWriterFactory`) — the app selects the correct reader/writer at runtime based on file format
- **Interface-based Reader/Writer abstractions** — CSV and JSON implementations are swappable without touching existing code
- **Comparator-based sorting** via nested static classes for flexible sort strategies by name and supplier
- **Unit testing with JUnit 5** covering reader/writer logic across both formats
- **Integration with third-party libraries** (opencsv, json-simple) for robust file parsing rather than hand-rolled parsing logic

## Setup
````bash
git clone https://github.com/Amestannies/product-inventory-system.git
cd product-inventory-system
javac -cp "lib/*" -d bin src/solution/*.java
java -cp "bin:lib/*" solution.ProductInventoryMenu
````
When prompted, enter `Products.csv` or `Products.json` to load the included sample data.

## Menu Options
````
A - Add Product              N - Print Products Sorted by Name
R - Remove Product           C - Print Products in a Category
U - Update Quantity in Stock O - Print Products to Reorder
S - Print Products Sorted by Supplier
Q - Quit
````

## Running Tests
Run `ProductReaderWriterTest` via your IDE's JUnit 5 runner.

## Project Structure
| Path | Contents |
|---|---|
| `src/solution/` | Current implementation |
| `archive/` | Earlier draft implementation, kept for reference (see `archive/README.md`) |
| `lib/` | Required third-party jars |

## What I'd Improve Next
- Migrate to Maven/Gradle so dependencies resolve automatically instead of being committed as jars
- Add a GUI (currently CLI-only)
