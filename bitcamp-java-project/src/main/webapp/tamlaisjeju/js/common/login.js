// express 추가
var express = require('express');
var app = express();

// passport 추가
var passport = require('passport');
var FacebookStrategy = require('passport-facebook').Strategy;
var KakaoStrategy = require('passport-kakao').Strategy;
var GoogleStrategy = require('passport-google-oauth20').Strategy;
var NaverStrategy = require('passport-naver').Strategy;

// jade...html 이거로 보고싶으면 하고
app.set('view engine', 'jade');
app.set('views', './views');
app.use(express.static('public'));


var data;

/*app.get('/', function(req, res){
    var log = `
    <a href="/auth">로그인</a>`
    res.send(log);
});*/

app.get('/welcome', function(req,res){
    res.redirect("http://localhost:8888/bitcamp-java-project/tamlaisjeju/index2.html")
});

app.get('/fail', function(req,res){
    res.send('로그인실패');
});

app.get('/logout', function(req, res){
    req.logout();
    res.redirect('http://localhost:8888/bitcamp-java-project/tamlaisjeju/index2.html');
});

app.get('/auth', function(req, res) {
    var output = `
        <a href="/auth/facebook">페이스북 로그인</a><br>
        <a href="/auth/kakao">카카오 로그인</a><br>
        <a href="/auth/google">구글 로그인</a><br>
        <a href="/auth/naver">네이버 로그인</a><br>
        <a href="/">로그인화면</a><br>
    `
    res.send(output);
});

//미들웨어 설정 필수!!!
app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser(function(user, done) {
    done(null, user);
});
  
 passport.deserializeUser(function(user, done) {
    done(null, user);
 });

// 페이스북 인증
passport.use(new FacebookStrategy({
    clientID: '228605734455848',
    clientSecret: 'e16581e68a8639de8bf57f08d7654665',
    callbackURL: "/auth/facebook/callback",
    profileFields: ['email']
  },
  function(accessToken, refreshToken, profile, done) {
    done(null,profile);
    }
));

app.get('/auth/facebook', passport.authenticate('facebook', {scope: ['email']}));

app.get('/auth/facebook/callback',
    passport.authenticate('facebook', { successRedirect: '/welcome',
                                        failureRedirect: '/auth' }
    )
);


// 카카오 인증
passport.use(new KakaoStrategy({
    clientID: 'cbfb710c30c958007d125829a9752b7c',
    callbackURL: "/auth/kakao/callback"
    },
    function(accessToken, refreshToken, profile, done){
        done(null,profile);
        console.log(profile);
        data = profile;
    }
));

app.get('/auth/kakao', passport.authenticate('kakao', {scope: ['profile']}));

app.get('/auth/kakao/callback',
    passport.authenticate('kakao', { successRedirect: '/welcome',
                                        failureRedirect: '/auth' }
    )
);

// 구글 인증
passport.use(new GoogleStrategy({
    clientID: '216742254118-u8424bjgqqohd6evp78ar2llqdd5uiv2.apps.googleusercontent.com',
    clientSecret: '-bW-0FIjukXhToiKItkHR--4',
    callbackURL: '/auth/google/callback'
    },
    function(accessToken, refreshToken, profile, done){
        done(null,profile);
        console.log(profile);
        data = profile;
    }
));

app.get('/auth/google', passport.authenticate('google', {scope: ['profile']}));

app.get('/auth/google/callback', passport.authenticate( 'google', { failureRedirect: '/auth' }),
    function(req, res) {
            res.redirect('/welcome'); 
});


// 네이버 인증
passport.use(new NaverStrategy({
    clientID: 'ot77q82amxODW5DFTVs6',
    clientSecret: 'vvIVCVGAPk',
    callbackURL: '/auth/naver/callback'
    },
    function(accessToken, refreshToken, profile, done){
        done(null,profile);
        console.log(profile);
        data = profile;
    }
));

app.get('/auth/naver', passport.authenticate('naver'));

app.get('/auth/naver/callback',
    passport.authenticate('naver', { successRedirect: '/welcome',
                                        failureRedirect: '/auth' }
    )
);

app.listen(8000);