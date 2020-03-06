FROM node:latest as builder

COPY package.json package-lock.json ./

RUN npm set progress-false && npm config set depth 0 && npm cache clean --force

## Storing node_modules on separate layer prevents unneccessary npm installs on each build
RUN npm i && mkdir /ng-app && cp -R ./node_modules ./ng-app

WORKDIR /ng-app

COPY . .

## Build angular app in production mode and store artifacts in /dist
RUN $(npm bin)/ng build --prod

FROM nginx:latest

## Copy default nginx config
COPY nginx/default.conf /etc/nginx/conf.d/

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From builder stage: copy artifcats in /dist to default public /nginx
COPY --from=builder /ng-app/dist/course-enrollment-client /user/share/nginx/html

## debug mode
CMD["nginx", "-g", "daemon off;"]
