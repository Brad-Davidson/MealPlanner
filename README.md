# MealPlanner
---
Design Document

## Introduction
---

This app will give you a variety of different recipes to choose from depending on what you are feeling on eating through the week.  This application will then break down the recipes to what ingredients you will need in order to cook them and make it into a shopping list for you to be able to be prepared when going to the grocery store.  One last important functionality will be being able to schedule times in which you plan to eat these meals in the day, and you will receive a notification of when you should eat your meal.

## Storyboard
---

![category page](https://user-images.githubusercontent.com/65302404/106343773-105a9680-6275-11eb-8c60-54820fa60255.jpg)
![meal schedule](https://user-images.githubusercontent.com/65302404/106343774-118bc380-6275-11eb-910e-0738656c8017.jpg)
![recipe list](https://user-images.githubusercontent.com/65302404/106343776-13ee1d80-6275-11eb-8fcb-6fa73de2c274.jpg)
![shoping list](https://user-images.githubusercontent.com/65302404/106343777-13ee1d80-6275-11eb-8c0b-40fc4edc7147.jpg)

## Functional Requirements
---

###Requirement 100.0: Search for recipes and plan meals
####SCENARIO
As a user interested in planning meals, I want to be able to search for recipes based on any part of the recipe; name of ingredient and name of meal I want to end up with. Users should be able to enter recipe into a meal planner to put together a weekly grocery list.
####DEPENDENCIES
Ingredient and recipe search data are available and easily accessible
All recipes can be slotted into a meal during the week (breakfast, lunch, or dinner) and be
compiled together into a grocery list
####ASSUMPTIONS
Ingredients are quantified consistently (i.e., garlic measure in cloves, pepperoni measured in pieces)
All recipes have a ‘time to prepare’ associated with them
####EXAMPLES
**1.1**
**Given** a feed of recipe data is available
**When** I search for ‘pizza’
**Then** I should receive at least one result with these attributes:	
**Recipe**: Pizza
**Ingredients**: Flour, pepperoni, cheese
**Time to prepare**: 45 minutes
 
**1.2**
Given a feed of recipe data is available
When I search for ‘garlic’
Then I should receive at least one result with these attributes:
	Recipe: Any involving garlic
	Ingredients: garlic
	Time to prepare: Varies by recipe
**1.3**
**Given** a feed of recipe data is available and I have found a recipe I want to make
**When** I want to plan the recipe for a meal slot during the week
**Then** I should be able to put a recipe in a planner
**Recipe**: Varies by recipe
**Ingredients**: Varies by recipe
**Time to prepare**: Varies by recipe
**Optional check-box**: Add to planner, specify day of week and what meal it is for (breakfast lunch or dinner)
**1.4**
**Given** a feed of recipe data is available
**When** I search for ‘askjhsdkjfbsjkdlbfvouihhjklukljh’
**Then** I should receive zero results.
