## Franklin Examples

A copy of the [Franklin introduction](https://azavea.github.io/franklin/docs/introduction).

`host.docker.internal` here is the reference to the DB locally available for the MacOS or Windows machine.
Check [Docker docs](https://docs.docker.com/docker-for-mac/networking/) for more details.

1. Run DB migrations

```bash
docker run --rm \
  quay.io/azavea/franklin:latest \
  migrate \
  --db-user franklin \
  --db-name franklin \
  --db-password franklin \
  --db-host host.docker.internal
```

2. Import STAC Catalog

```bash
docker run --rm \
  -v ~/.aws:/var/lib/franklin/.aws \
  quay.io/azavea/franklin:latest \
  import-catalog \
  --db-user franklin \
  --db-name franklin \
  --db-password franklin \
  --db-host host.docker.internal \
  --catalog-root s3://geotrellis-workshop/shade-out-catalog-small/catalog.json
``` 

3. Run Franklin instance

```bash
docker run --rm \
  -p 9090:9090 \
  quay.io/azavea/franklin:latest \
  serve \
  --db-user franklin \
  --db-name franklin \
  --db-password franklin \
  --db-host host.docker.internal 
```
