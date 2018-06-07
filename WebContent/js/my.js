my={};
my.base64_encode=function(value){
	var str=CryptoJS.enc.Utf8.parse(value);
	var base64=CryptoJS.enc.Base64.stringify(str);
	return base64;
};

my.base64_decode=function(value){
	var words  = CryptoJS.enc.Base64.parse(value);
	return words;
};

my.encode=function(value){
	return encodeURI(encodeURI(value));
};
