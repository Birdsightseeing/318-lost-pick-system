# User Stories & Specifications

## User Story Overview

| Service | User Story ID | User Story Name | Role(s) |
| :--- | :--- | :--- | :--- |
| Lost Service | B1 | Create / Get / Update / Delete a lost item | User, Admin |
| Lost Service | B2 | List all lost items | User, Admin |
| Found Service | L1 | Find found items by name | User, Admin |
| Found Service | L2 | Check item status | User, Admin |
| Found Service | L3 | Match a lost item with found items | User, Admin |

---

## Item Domain Schema
All item records (lost or found) share the following core attributes:
- **`itemId`** (Long): Unique identifier for the item record.
- **`name`** (String): Name/title of the item (e.g., "iPhone 15 Pro", "Blue Backpack").
- **`color`** (String): Primary color of the item.
- **`category`** (String): Category of the item (e.g., "Electronics", "Bags", "Keys", "Documents").
- **`location`** (String): Location where the item was lost or found.
- **`status`** (Enum): Status of the item record:
  - `LOST`: Item has been reported lost.
  - `FOUND`: Item has been reported found.
  - `MATCHED`: Item has been matched between lost and found records.
  - `CLAIMED`: Item has been returned to its owner.

---

## Lost Service

### User Story B1: Create / Get / Update / Delete a lost item
- **As a User**, I want to create a new lost item record with details such as `name`, `color`, `category`, `location`, and initial `status` set to `LOST`.
- **As a User/Admin**, I want to retrieve a lost item's information by its `itemId` or `name`.
- **As an Admin**, I want to update an existing lost item's details (such as `location`, `status`, or `color`) by its `itemId`.
- **As an Admin**, I want to delete a lost item record by its `itemId`.

### User Story B2: List all lost items
- **As a User/Admin**, I want to retrieve a list of all registered lost items.

---

## Found Service

### User Story L1: Find found items by name
- **As a User/Admin**, I want to search and retrieve found item records that match a specific `name` or `category`.

### User Story L2: Check item status
- **As a User/Admin**, I want to check the current `status` (`FOUND`, `MATCHED`, `CLAIMED`) of a specific item by its `itemId`.

### User Story L3: Match a lost item with found items
- **As a User/Admin**, I want to execute a matching search that compares a lost item's `name`, `category`, and `location` against all registered found items to identify candidate matches and update item status to `MATCHED` when confirmed.
