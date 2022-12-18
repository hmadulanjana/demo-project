FROM node:18-alpine AS base

RUN npm i -g pnpm

WORKDIR /app
COPY package.json pnpm-lock.yaml ./
RUN pnpm install

FROM base AS build

WORKDIR /app
COPY . .
COPY --from=dependencies /app/node_modules ./node_modules
RUN pnpm build
# RUN pnpm prune --prod # Optional: Remove devDependencies

FROM base as deploy

WORKDIR /app
COPY --from=build /app/dist/ ./dist/
COPY --from=build /app/node_modules ./node_modules

# CMD ["node", "dist/main.js"]
CMD ["pnpm", "preview"]
