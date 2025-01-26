Remaining -> cache getEventsById api using redis.
Instead of pushing the changes directly to db upon reserving, push it to cache and upon successful booking, push to db
