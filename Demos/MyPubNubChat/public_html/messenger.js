/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 $(document).ready(function () {
  // Initialize the PubNub API connection.
  var pubnub = PUBNUB({
    publish_key: 'pub-c-799209a5-cd17-477f-92be-c31f29f13391',
    subscribe_key: 'sub-c-044a1e54-fcab-11e5-a492-02ee2ddab7fe'
  });
 
  // Grab references for all of our elements.
  var messageContent = $('#messageContent'),
      sendMessageButton = $('#sendMessageButton'),
      messageList = $('#messageList');
 
  // Handles all the messages coming in from pubnub.subscribe.
  function handleMessage(message) {
    var messageEl = $("<li class='message'>"
        + "<span class='username'>" + message.data.chatUser + ": </span>"
        + message.data.chatMsg
        + "</li>");
    messageList.append(messageEl);
    messageList.listview('refresh');
 
    // Scroll to bottom of page
    $("html, body").animate({ scrollTop: $(document).height() - $(window).height() }, 'slow');
  };
 
  // Compose and send a message when the user clicks our send message button.
  sendMessageButton.click(function (event) {
    var message = messageContent.val();
 
    if (message != '') {
      pubnub.publish({
        channel: '198',
        message:{
        type: "groupMessage",
          data: {
            chatUser: 'test',
            chatMsg: message,
            chatTime: 1460543692229
          }
        }
      });
 
      messageContent.val("");
    }
  });
 
  // Also send a message when the user hits the enter button in the text area.
  messageContent.bind('keydown', function (event) {
    if((event.keyCode || event.charCode) !== 13) return true;
    sendMessageButton.click();
    return false;
  });
 
  // Subscribe to messages coming in from the channel.
  pubnub.subscribe({
    channel: '198',
    message: handleMessage
  });
});
