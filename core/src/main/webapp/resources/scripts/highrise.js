function initHighrise() {
	
	$('form select').change( function(){
		
		$.post('userDealsDetail', 
			{id:this.value},
			function(result){
					console.log(result);
			});
		
	});
	
}

