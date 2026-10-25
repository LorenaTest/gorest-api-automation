
@Regression
Feature: User

  Rule: The user needs email format

    Scenario: CRUD User

      # Create User
      When send POST request "/users" with
      """
      {
        "name": "Test User",
        "email": "test.user.251025@example.com",
        "gender": "male",
        "status": "active"
      }
      """
      Then code should be 201
      And the "name" should be "Test User"
      And the "gender" should be "male"
      And the "status" should be "active"
      And save "id" in "ID"

      # Read User
      When send GET request "/users/ID" with
      """
      """
      Then code should be 200
      And the "name" should be "Test User"
      And the "status" should be "active"

      # Update User
      When send PUT request "/users/ID" with
      """
      {
        "name": "Updated Test User",
        "email": "test.user.251025@example.com",
        "gender": "male",
        "status": "inactive"
      }
      """
      Then code should be 200
      And the "name" should be "Updated Test User"
      And the "status" should be "inactive"

      # Delete User
      When send DELETE request "/users/ID" with
      """
      """
      Then code should be 204

