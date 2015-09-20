angular.module('text2netApp',['ngResource']); //text2net is our main module. Depends on ngResource

angular.module('text2net.services').factory('text2net', function($resource) { //first parameter is resource name
  return $resource('https://aqueous-springs-2352.herokuapp.com/text2net/:id'); // Note the full endpoint address
});

.controller('PlayListCtrl',function($scope) {
	$scope.musicItemList = [
		{"title":"Dear Future Husband", "artist":"Meghan Trainor", "avatarUrl":"http://www.billboard.com.br/wp-content/uploads/2015/07/mghntrnr13lpsrmvn12_10.jpg", "albumUrl":"http://static.idolator.com/uploads/2014/10/meghan-trainor-title-cover.jpg","upVotes":83 , "downVotes":22},
		{"title":"Roar", "artist":"Katy Perry", "avatarUrl":"http://cdn.playbuzz.com/cdn/d80373c8-ebb9-49fa-8f7c-c849c534daf8/35df0ccf-f962-4d5f-8377-8707f2e94cef.jpg", "albumUrl":"http://www.canalgama.com.br/wp-content/uploads/2013/11/katy-perry-unconditionally.png","upVotes":128 , "downVotes":2},
		{"title":"Beat it", "artist":"Michael Jackson", "avatarUrl":"http://dtudo1pouco.com/wp-content/uploads/MICHAEL-JACKSON-michael-jackson-10317030-1082-1263.jpg", "albumUrl":"https://upload.wikimedia.org/wikipedia/en/6/65/Michael_Jackson_-_Beat_It_cover.jpg","upVotes":8 , "downVotes":2},
		{"title":"Soundcheck (Feat. Hanz On & Carlton Fisk)", "artist":"Method Men", "avatarUrl":"http://www.djchonz.com/wp-content/uploads/2015/06/MethodMan2.jpg", "albumUrl":"http://wutangclan.com/wp-content/uploads/2015/07/the-meth-lab-cover.jpg","upVotes":8 , "downVotes":2},
		{"title":"Give It Up Or Turnit A Loose (In The Jungle Groove Remix)", "artist":"James Brown", "avatarUrl":"http://i.imgur.com/4LREzhM.jpg", "albumUrl":"http://img.youtube.com/vi/fEVnFGnjnGU/0.jpg","upVotes":8 , "downVotes":2},
		{"title":"Show das poderosas", "artist":"Anita", "avatarUrl":"http://www.revistaclips.com/wp-content/uploads/2013/07/mc-anitta-musica.jpg", "albumUrl":"https://upload.wikimedia.org/wikipedia/en/8/8c/Anitta_Show_das_Poderosas.jpg","upVotes":9 , "downVotes":2},
		{"title":"Dear Future Husband", "artist":"Meghan Trainor", "avatarUrl":"http://www.billboard.com.br/wp-content/uploads/2015/07/mghntrnr13lpsrmvn12_10.jpg", "albumUrl":"http://static.idolator.com/uploads/2014/10/meghan-trainor-title-cover.jpg","upVotes":83 , "downVotes":22},
		{"title":"Roar", "artist":"Katy Perry", "avatarUrl":"http://cdn.playbuzz.com/cdn/d80373c8-ebb9-49fa-8f7c-c849c534daf8/35df0ccf-f962-4d5f-8377-8707f2e94cef.jpg", "albumUrl":"http://www.canalgama.com.br/wp-content/uploads/2013/11/katy-perry-unconditionally.png","upVotes":128 , "downVotes":2},
		{"title":"Beat it", "artist":"Michael Jackson", "avatarUrl":"http://dtudo1pouco.com/wp-content/uploads/MICHAEL-JACKSON-michael-jackson-10317030-1082-1263.jpg", "albumUrl":"https://upload.wikimedia.org/wikipedia/en/6/65/Michael_Jackson_-_Beat_It_cover.jpg","upVotes":8 , "downVotes":2},
		{"title":"Soundcheck (Feat. Hanz On & Carlton Fisk)", "artist":"Method Men", "avatarUrl":"http://www.djchonz.com/wp-content/uploads/2015/06/MethodMan2.jpg", "albumUrl":"http://wutangclan.com/wp-content/uploads/2015/07/the-meth-lab-cover.jpg","upVotes":8 , "downVotes":2},
		{"title":"Give It Up Or Turnit A Loose (In The Jungle Groove Remix)", "artist":"James Brown", "avatarUrl":"http://i.imgur.com/4LREzhM.jpg", "albumUrl":"http://img.youtube.com/vi/fEVnFGnjnGU/0.jpg","upVotes":8 , "downVotes":2},
		{"title":"Show das poderosas", "artist":"Anita", "avatarUrl":"http://www.revistaclips.com/wp-content/uploads/2013/07/mc-anitta-musica.jpg", "albumUrl":"https://upload.wikimedia.org/wikipedia/en/8/8c/Anitta_Show_das_Poderosas.jpg","upVotes":9 , "downVotes":2}
		
		
	];
	
	
});