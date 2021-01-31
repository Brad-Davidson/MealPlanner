# MealPlanner

Design Document

## Introduction

This app will give you a variety of different recipes to choose from depending on what you are feeling on eating through the week.  This application will then break down the recipes to what ingredients you will need in order to cook them and make it into a shopping list for you to be able to be prepared when going to the grocery store.  One last important functionality will be being able to schedule times in which you plan to eat these meals in the day, and you will receive a notification of when you should eat your meal.

## Storyboard

![category page](https://user-images.githubusercontent.com/65302404/106343773-105a9680-6275-11eb-8c60-54820fa60255.jpg)
![meal schedule](https://user-images.githubusercontent.com/65302404/106343774-118bc380-6275-11eb-910e-0738656c8017.jpg)
![recipe list](https://user-images.githubusercontent.com/65302404/106343776-13ee1d80-6275-11eb-8fcb-6fa73de2c274.jpg)
![shoping list](https://user-images.githubusercontent.com/65302404/106343777-13ee1d80-6275-11eb-8c0b-40fc4edc7147.jpg)

## Functional Requirements

### Requirement 100.0: Search for recipes
#### SCENARIO
As a user interested in planning meals, I want to be able to search for recipes based on any part of the recipe; name of ingredient or name of meal I want to end up with.
#### DEPENDENCIES
Ingredient and recipe search data are available and easily accessible
#### ASSUMPTIONS
Recipes are listed in English
Recipes are named consistently
#### EXAMPLES
**1.1**

**Given** a feed of recipe data is available

**When** I search for ‘pizza’

**Then** I should receive at least one result with these attributes:

**Recipe**: Pizza

**Ingredients**: Flour, pepperoni, cheese

**Time to prepare**: 45 minutes (varies by kind of pizza)

**1.2**

**Given** a feed of recipe data is available

**When** I search for ‘garlic’

**Then** I should receive at least one result with these attributes:

- Recipe: Any involving garlic
- Ingredients: garlic
- Time to prepare: Varies by recipe

**1.3**

**Given** a feed of recipe data is available

**When** I search for ‘askjhsdkjfbsjkdlbfvouihhjklukljh’

**Then** I should receive zero results.


### Requirement 101.0: Save recipes into planner
#### SCENARIO
As a user that has pulled search results for recipes, I would like to save recipes to a meal slot during the week.
#### DEPENDENCIES
Ingredient and recipe search data are available and easily accessible
All recipes can be slotted into a meal during the week (breakfast, lunch, or dinner)
#### ASSUMPTIONS
Ingredients are quantified consistently (i.e., garlic measure in cloves, pepperoni measured in pieces)
#### EXAMPLES
**2.1**

**Given** a feed of recipe data is available and I have found a recipe I want to make

**When** I want to plan the recipe for a meal slot during the week

**Then** I should be able to put a recipe in a planner for ‘Breakfast’, ‘lunch’, or ‘dinner’ on specified day of week

**Recipe**: Varies by recipe

**Ingredients**: Varies by recipe

**Time to prepare**: Varies by recipe

**Button**: Add to planner, specify day of week and what meal it is for (breakfast lunch or dinner)

**2.2**

**Given** a feed of recipe data is available and I have found a recipe I want to make

**When** I want to plan the recipe for a meal slot on a day that has already passed

**Then** I should receive an error preventing me to plan a meal that day

### Requirement 102.0: Retrieve shopping list of ingredients
#### SCENARIO
As a user interested in planning meals, I want to be able to use my week of planned recipes and compile a shopping list
#### DEPENDENCIES
Ingredient and recipe search data are available and easily accessible
Week of planned meals has data stored
All recipes can be slotted into a meal during the week (breakfast, lunch, or dinner) and be
compiled together into a grocery list
#### ASSUMPTIONS
Ingredients are quantified consistently (i.e., garlic measure in cloves, pepperoni measured in pieces)
All recipes have a ‘time to prepare’ associated with them
Ingredients can be consolidated easily, so one ingredient is not listed twice in different amounts because the app could not combine them
#### EXAMPLES
**3.1**

**Given** a feed of recipe data is available, I have found a recipe I want to make, and it is put in a meal slot during the week.

**When** I want to know what ingredients to buy

**Then** I should be able to put a shopping list together based on ingredients listed in the saved recipe

**Ingredients**: Totals combined from all planned recipes for the week

## Class Diagram
![class diagram](https://user-images.githubusercontent.com/65302404/106375414-ceefe700-6359-11eb-8fcf-1da52c8c7195.PNG)

## Class Diagram Description

**MainScreen**: The screen that comes up when the app is started. This will have a a list of the various recipes the user can select.

**RecipeDetails**: A screen that shows details of an recipe.

**MealPlanDetails**: A screen that shows the shopping list story of the user.

**RetrofitInstance**: Boostrap class required for Retrofit.

**Recipe**: Noun class that represents a recipe.

**Ingredient**: Noun class that represents an ingredient.

**MealPlan**: Noun class that represents a meal plan.

**IRecipeDAO**: Interface for Retrofit to find and parse Recipe JSON.

**IIngredientDAO**: Interface for MealPlan to persist Ingredient data.

**IMealPlanDAO**: Interface for MealPlan to persist MealPlan data.


## Scrum Roles

- DevOps/Product Owner/Scrum Master: Bradley Davidson
- Front End: Jacob Rinehardt
- Integration Specialists: Joshua Bodenstein & Jacob Gormly

## Weekly Meeting

Tuesday at 4:30 PM. Use this link teams meeting link:

Meeting Information
[Teams Meeting](https://teams.microsoft.com/l/meetup-join/19%3ameeting_NmUyNjY3MTctZTY1Ny00NjFjLThlNjQtY2UzMWUzZjAzYjFm%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%22684f904f-b75c-4fec-8ab6-78adff6e71ee%22%7d)
