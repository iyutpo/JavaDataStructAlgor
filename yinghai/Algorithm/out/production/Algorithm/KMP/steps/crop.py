from PIL import Image






if __name__ == '__main__':
    pre = '工作簿1_Page_'
    mid = [str(i) for i in range(1, 30)]
    for i in range(9):
        mid[i] = '0' + mid[i]
    name = []
    for i in range(len(mid)):
        name.append(pre + mid[i] + '.jpg')

    left = 300
    top = 350
    right = 3739
    bottom = 600
    for i in range(len(name)):
        im = Image.open(name[i])
        im2 = im.crop((left, top, right, bottom))
        im2.save('./output/' + name[i])


    # im = Image.open('工作簿1_Page_01.jpg')
    # left = 300
    # top = 350
    # right = 3739
    # bottom = 600
    # im2 = im.crop((left, top, right, bottom))
    # print(im.size)
    # im2.show()
    # im2.save('test.jpg')
