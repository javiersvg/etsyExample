Register in renmanmb.com

Meta:
@category registration
@site renmanmb

Narrative:

In order to get a user account in remnanmb.com
As a user
I want to complete the registration process

Scenario: Registering with an existing, but not active, mail and screen name

Given I am on renmanmb.com
When I go to the Registration page
Then I see the registration page

When I register with an existing mail and username
Then I see the error page
And I see an error message indicating that the mail is nor available nor confirmed either