Android Coding Test
===================

My thoughts on the test:


I have completed the test within the 2 hours limitation, however, I didn't really find the test appropriate. Please let me explain:

1. The 2-hour limit set, forces the programmer to prioritise his/her time to completing the 6 requirements requested : i. the bug fix and ii. to vi. the new functionality.
2. The whole app needs to be re-architected. For example, it needs to be re-factored to use MVP. 
3. There needs to be a separation of concern, so that the View talks to the Presenter, and the Presenter doesn't directly talk to the View. The Presenter calls the Model, and we use the Repository Pattern, so that the Model negotiates whether to retrieve the data from the network or local db. Again, the Model doesn't directly talk to the Presenter, except through the interface ( Dependency Inversion). By doing so, we can use, or can reuse the functionality these layers offer, without hard-wiring our coupling, from other calling clients.
4. After having this separation between the data and the view, we can use our Presenter for unit testing. To facilitate unit testing ( ie testing on the JVM ), we make the presenter framework-independent, ie no Android-specific information is passed to the Presenter, eg context. 
5. Coming back to this example. I would like to add 2-pane functionality, so that if the device is a tablet and in landscape mode, then I would use a 2-pane view, with the detailed view, on the right hand side.
6. The configuration change , causes the data to be re-fetched. Which, isn't correct behaviour. So, this also needs to be rectified.
7. We need to add the logic to retrieve the data into the model, this way, if we later change to a db, eg ROOM or Realm, it won't affect the rest of the application.
8. There needs to be a spinner shown, whilst the data is being retrieved, in case of slow connection. Currently, the app is blank , during the loading phase. 
9. We should introduce some unit tests using Mockito, to test the logic, eg Contacts with same hobbies, if Web service is down, which we mock , we can show the correct display message.
