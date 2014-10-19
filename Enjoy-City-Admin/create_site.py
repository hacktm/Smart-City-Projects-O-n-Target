from django.contrib.sites.models import Site

if __name__ == "__main__":
    site = Site()
    site.domain = 'enjoy.com'
    site.name = 'Enjoy Your City'
    site.save()

    print site.id
