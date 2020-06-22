rem kompiliert die app in tomcat's web-ordner

cd ./parkhaus
ng build --prod --delete-output-path false --output-hashing none
cd ..
