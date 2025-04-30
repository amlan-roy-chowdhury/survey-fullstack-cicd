# Stage 1: Build the app
FROM node:18 as build-stage

WORKDIR /app
COPY frontend/package*.json ./
COPY frontend/ .

RUN npm install
RUN npm run build

# Stage 2: Serve with nginx
FROM nginx:alpine as production-stage

COPY --from=build-stage /app/dist /usr/share/nginx/html

# Replace default nginx config (optional, improves SPA routing)
COPY infra/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


