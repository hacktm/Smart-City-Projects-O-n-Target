from django.contrib.sites.models import Site

if __name__ == "__main__":
    site = Site()
    site.domain = 'enjoy.com'
    site.name = 'Enjoy The City'
    site.save()

    print site.id
